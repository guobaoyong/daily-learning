package qqzsh.top.hd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import qqzsh.top.hd.pojo.BGM;
import qqzsh.top.hd.pojo.User;
import qqzsh.top.hd.pojo.Video;
import qqzsh.top.hd.pojo.vo.*;
import qqzsh.top.hd.service.*;
import qqzsh.top.hd.utils.JSONResult;
import qqzsh.top.hd.utils.LayUIResponse;
import qqzsh.top.hd.utils.MD5Utils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-06-03 18:00
 * @description 管理员全部controller
 */

@RequestMapping("/admin")
@Api(value = "管理员接口",tags = {"管理员的controller"})
@RestController
public class AdminController extends BasicController{

    @Autowired
    private UserService userService;
    @Autowired
    private UserReportService userReportService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private BGMService bgmService;
    @Autowired
    private CommentsService commentsService;

    @GetMapping("/judge/{address}")
    public ModelAndView index(@PathVariable("address") String address,HttpSession session){
        try {
            UserVO admin = (UserVO) session.getAttribute("admin");
            if (admin.getRole() == 0 && admin != null){
                if (address.equals("index"))  //首页
                    return new ModelAndView("index");
                else if (address.equals("welcome")){  //欢迎页
                    return new ModelAndView("pages/welcome").addObject("userCount",userService.sum())
                            .addObject("reportCount",userReportService.sumBySelect("0"))
                            .addObject("videoCount",videoService.sum())
                            .addObject("bgmCount",bgmService.sum());
                }else if (address.equals("logout")){ //退出
                    if (admin.getRole() == 0 && admin != null){
                        redisOperator.del(USER_REDIS_SESSION+":"+admin.getId());
                        session.removeAttribute("admin");
                        return new ModelAndView("login");
                    }
                    return new ModelAndView("login");
                }else if (address.equals("member_list")){  //用户列表
                    return new ModelAndView("pages/user/list");
                }else if(address.equals("user_add")){  //用户添加
                    return new ModelAndView("pages/user/add");
                }else if(address.equals("user_info")){  //用户查看个人信息
                    return new ModelAndView("pages/user/info").addObject("user",admin);
                } else if (address.equals("report_list")){  //举报列表
                    return new ModelAndView("pages/report/list").addObject("processed",userReportService.sumBySelect("0"));
                }else if (address.equals("video_list")){  //视频列表
                    return new ModelAndView("pages/video/list").addObject("processed",videoService.sum());
                }else if (address.equals("bgm_list")){ //背景音乐列表
                    return new ModelAndView("pages/bgm/list").addObject("processed",bgmService.sum());
                }else if (address.equals("bgm_add")){ //背景音乐添加
                    return new ModelAndView("pages/bgm/add");
                }else if (address.equals("comment_list")){ //评论列表
                    return new ModelAndView("pages/comment/list").addObject("processed",commentsService.selectCount(null));
                }
            }else {
                return new ModelAndView("login");
            }
            return new ModelAndView("login");
        }catch (Exception e){
            return new ModelAndView("login");
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true,
                    dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,
                    dataType = "String",paramType = "form")
    })
    @PostMapping("/login")
    public JSONResult login(String username, String password, HttpSession session) throws Exception {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return JSONResult.errorMsg("用户名或密码为空");
        }
        User currentUser = userService.findByUserName(username);
        if (currentUser == null){
            return JSONResult.errorMsg("用户不存在！");
        }else if (currentUser.getPassword().equals(MD5Utils.getMD5Str(password))){
            if (currentUser.getRole() == 0){
                currentUser.setPassword("");
                String uniqueToken = UUID.randomUUID().toString();
                redisOperator.set(USER_REDIS_SESSION+":"+currentUser.getId(),
                        uniqueToken,60*15);
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(currentUser,userVO);
                userVO.setUserToken(uniqueToken);
                session.setAttribute("admin",userVO);
                return JSONResult.ok(userVO);
            }else {
                return JSONResult.errorMsg("用户非管理员，不可登录！");
            }
        }else {
            return JSONResult.errorMsg("密码错误！");
        }
    }

    @GetMapping("/userList")
    public LayUIResponse<List<User>> userList(Model model,Integer page, Integer limit,
                                              @RequestParam(name = "search_value",required = false) String search_value){
        Page<User> puser = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (search_value != null){
            wrapper.like("username",search_value);
            model.addAttribute("search_value",search_value);
        }
        IPage<User> iPage = userService.page(puser, wrapper);
        return new LayUIResponse(0,"查询成功",iPage.getTotal(),iPage.getRecords());
    }

    @PostMapping("/user_delete")
    public JSONResult user_delete(Integer id,HttpSession session){
        try {
            UserVO admin = (UserVO) session.getAttribute("admin");
            if (admin.getId() == id){
                return JSONResult.errorMsg("管理员不能删除自己！");
            }else {
                userService.delete(id);
                return JSONResult.ok();
            }
        }catch (Exception e){
            return JSONResult.errorMsg("删除出错，请重试！");
        }
    }

    @PostMapping("/user_update")
    public JSONResult user_update(Integer id,String field,String value) {
        try {
            User user = userService.findById(id);
            if (user != null){
                if (field.equals("username")){
                    if (userService.findByUserName(value) == null){
                        user.setUsername(value);
                        userService.update(user);
                    }else {
                        return JSONResult.errorMsg("用户名已存在");
                    }
                }else if (field.equals("password")){
                    value = MD5Utils.getMD5Str(value);
                    user.setPassword(value);
                    userService.update(user);
                }else if (field.equals("role")){
                    if (value.equals("0") || value.equals("1")){
                        user.setRole(Integer.parseInt(value));
                        userService.update(user);
                    }else {
                        return JSONResult.errorMsg("请正确填写角色，0代表管理员，1代表普通用户");
                    }
                }
                return JSONResult.ok();
            }else {
                return JSONResult.errorMsg("修改出错，请重试");
            }
        }catch (Exception e){
            return JSONResult.errorMsg("修改出错，请重试");
        }
    }

    @PostMapping("/user_add")
    public JSONResult user_add(User user,
                               @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        System.out.println(user);
        User byUserName = userService.findByUserName(user.getUsername());
        if (byUserName == null){
            user.setFans_counts(0);
            user.setFollow_counts(0);
            user.setReceive_like_counts(0);
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            userService.insert(user);
            User user2 = userService.findByUserName(user.getUsername());
            if (file != null){
                //做头像上传
                //文件保存的命名空间
                String fileSpace = "res";
                //保存到数据库的路径
                String uploadPathDB = "/" + user2.getId() + "/face";
                FileOutputStream fileOutputStream = null;
                InputStream inputStream = null;
                String filename = file.getOriginalFilename();
                try {
                    if (StringUtils.isNotBlank(filename)){
                        //文件上传的最终路径
                        String finalPath = fileSpace + uploadPathDB + "/" + filename;
                        //设置数据库的保存路径
                        uploadPathDB += ("/" + filename);
                        File outfile = new File(finalPath);
                        if (outfile.getParentFile() != null || !outfile.getParentFile().isDirectory()){
                            //创建文件夹
                            outfile.getParentFile().mkdirs();
                        }
                        fileOutputStream = new FileOutputStream(outfile);
                        inputStream = file.getInputStream();
                        IOUtils.copy(inputStream,fileOutputStream);
                    }
                } catch (Exception e) {
                    return JSONResult.errorMsg("上传出错...");
                }finally {
                    if (fileOutputStream != null){
                        try {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                user2.setFace_image(uploadPathDB);
                userService.update(user2);
            }
        }else {
            return JSONResult.errorMsg("用户名已存在");
        }
        return JSONResult.ok();
    }

    @GetMapping("/reportList")
    public LayUIResponse<List<UsersReportVO>> reportList(Model model, Integer page, Integer limit,
                                                         @RequestParam(name = "search_value",required = false) String search_value){
        Page<UsersReportVO> puser = new Page<>(page, limit);
        List<UsersReportVO> userVOS = new ArrayList<>();
        if (search_value != null){
            model.addAttribute("search_value",search_value);
            try {
                Integer.parseInt(search_value);
                userVOS = userReportService.queryAllByStatus(puser,search_value);
            }catch (NumberFormatException e){
                userVOS = userReportService.queryAllBySelect(puser,search_value);
            }
            return new LayUIResponse(0,"查询成功",(long)userReportService.sumBySelect(search_value),userVOS);
        }else {
            userVOS = userReportService.queryAll(puser);
            return new LayUIResponse(0,"查询成功",(long)userReportService.sum(),userVOS);
        }

    }

    @PostMapping("/report_update")
    public JSONResult report_update(Integer id,String field,String value) {
        if (field.equals("status")) {
            if (value.equals("0") || value.equals("1") || value.equals("2")) {
                userReportService.update(id, field, value);
            }else {
                return JSONResult.errorMsg("请正确填写处理状态，未处理是0，处理中是1，已处理是2");
            }
        }
        return JSONResult.ok();
    }

    @PostMapping("/report_delete")
    public JSONResult report_delete(Integer id){
        userReportService.delete(id);
        return JSONResult.ok();
    }

    @GetMapping("/videoList")
    public LayUIResponse<List<VideoVO>> videoList(Model model, Integer page, Integer limit,
                                                   @RequestParam(name = "search_value",required = false) String search_value){
        Page<VideoVO> puser = new Page<>(page, limit);
        List<VideoVO> userVOS = new ArrayList<>();
        if (search_value != null){
            model.addAttribute("search_value",search_value);
            try {
                Integer.parseInt(search_value);
                userVOS = videoService.queryVideoStatus(puser,search_value);
            }catch (NumberFormatException e){
                userVOS = videoService.queryVideoSelects(puser,search_value);
            }
            return new LayUIResponse(0,"查询成功",(long)videoService.sumBySelect(search_value),userVOS);
        }else {
            userVOS = videoService.queryVideoAll(puser);
            return new LayUIResponse(0,"查询成功",(long)videoService.sum(),userVOS);
        }
    }

    @PostMapping("/video_delete")
    public JSONResult video_delete(Integer id){
        videoService.delete(id);
        return JSONResult.ok();
    }

    @PostMapping("/video_update")
    public JSONResult video_update(Integer id,String field,String value) {
        if (field.equals("status")) {
            if (value.equals("1") || value.equals("2")) {
                Video video = videoService.getById(id);
                video.setStatus(Integer.parseInt(value));
                videoService.update(video);
            }else {
                return JSONResult.errorMsg("请正确填写状态，正常是1，禁播是2");
            }
        }
        return JSONResult.ok();
    }

    @GetMapping("/bgmList")
    public LayUIResponse<List<BGMVO>> bgmList(Model model, Integer page, Integer limit,
                                                @RequestParam(name = "search_value",required = false) String search_value){
        Page<BGMVO> puser = new Page<>(page, limit);
        List<BGMVO> userVOS = new ArrayList<>();
        if (search_value != null){
            model.addAttribute("search_value",search_value);
            userVOS = bgmService.queryAllSelects(puser,search_value);
            return new LayUIResponse(0,"查询成功",(long)userVOS.size(),userVOS);
        }else {
            userVOS = bgmService.queryAll(puser);
            return new LayUIResponse(0,"查询成功",(long)bgmService.sum(),userVOS);
        }
    }

    @PostMapping("/bgm_delete")
    public JSONResult bgm_delete(Integer id){
        bgmService.delete(id);
        return JSONResult.ok();
    }

    @PostMapping("/bgm_update")
    public JSONResult bgm_update(Integer id,String field,String value) {
        try {
            BGM bgm = bgmService.queryBgmById(id);
            if (bgm != null){
                if (field.equals("author")){
                    bgm.setAuthor(value);

                }else if (field.equals("name")){
                    bgm.setName(value);
                }
                bgmService.update(bgm);
                return JSONResult.ok();
            }else {
                return JSONResult.errorMsg("修改出错，请重试");
            }
        }catch (Exception e){
            return JSONResult.errorMsg("修改出错，请重试");
        }
    }

    @PostMapping("/bgm_add")
    public JSONResult bgm_add(BGM bgm,
                               @RequestParam(value = "file", required = true) MultipartFile file,
                              HttpSession session) {
        try {
            UserVO user = (UserVO)session.getAttribute("admin");
            if (user != null){
                bgm.setUser_id(user.getId());
                if (file != null){
                    //做头像上传
                    //文件保存的命名空间
                    String fileSpace = "res";
                    //保存到数据库的路径
                    String uploadPathDB = "/" + bgm.getUser_id() + "/bgms";
                    FileOutputStream fileOutputStream = null;
                    InputStream inputStream = null;
                    String filename = file.getOriginalFilename();
                    try {
                        if (StringUtils.isNotBlank(filename)){
                            //文件上传的最终路径
                            String finalPath = fileSpace + uploadPathDB + "/" + filename;
                            //设置数据库的保存路径
                            uploadPathDB += ("/" + filename);
                            File outfile = new File(finalPath);
                            if (outfile.getParentFile() != null || !outfile.getParentFile().isDirectory()){
                                //创建文件夹
                                outfile.getParentFile().mkdirs();
                            }
                            fileOutputStream = new FileOutputStream(outfile);
                            inputStream = file.getInputStream();
                            IOUtils.copy(inputStream,fileOutputStream);
                        }
                    } catch (Exception e) {
                        return JSONResult.errorMsg("上传出错...");
                    }finally {
                        if (fileOutputStream != null){
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    bgm.setPath(uploadPathDB);
                    bgmService.add(bgm);
                }else {
                    return JSONResult.errorMsg("请选择文件后上传");
                }
            }
        }catch (Exception e){
            return JSONResult.errorMsg("上传出错，请重试！");
        }
        return JSONResult.ok();
    }

    @GetMapping("/commentList")
    public LayUIResponse<List<CommentsVO>> commentList(Model model, Integer page, Integer limit,
                                                   @RequestParam(name = "search_value",required = false) String search_value){
        Page<CommentsVO> puser = new Page<>(page, limit);
        List<CommentsVO> userVOS = new ArrayList<>();
        if (search_value != null){
            model.addAttribute("search_value",search_value);
            userVOS = commentsService.getAllCommentsSelects(puser,search_value);
        }else {
            userVOS = commentsService.getAllComments(puser);
        }
        return new LayUIResponse(0,"查询成功",(long)commentsService.selectCount(search_value),userVOS);
    }

    @PostMapping("/comment_delete")
    public JSONResult comment_delete(Integer id){
        commentsService.delete(id);
        return JSONResult.ok();
    }






}
