package qqzsh.top.hd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qqzsh.top.hd.pojo.*;
import qqzsh.top.hd.pojo.vo.UserVO;
import qqzsh.top.hd.service.UserService;
import qqzsh.top.hd.utils.JSONResult;

import java.io.*;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:04
 * @description
 */
@RestController
@Api(value = "用户相关业务的接口",tags = {"用户相关业务的controller"})
@RequestMapping("/user")
public class UserController extends BasicController{

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户上传头像",notes = "用户上传头像接口")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,
            dataType = "int",paramType = "query")
    @PostMapping("/uploadFace")
    public JSONResult uploadFace(Integer userId,
                                 @RequestParam("file") MultipartFile[] files) {
        if (userId == null){
            return JSONResult.errorMsg("用户userId不能为空！");
        }
        //文件保存的命名空间
        String fileSpace = "res";
        //保存到数据库的路径
        String uploadPathDB = "/" + userId + "/face";
        if (files != null && files.length > 0){
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            String filename = files[0].getOriginalFilename();
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
                    inputStream = files[0].getInputStream();
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
        }else {
            return JSONResult.errorMsg("上传出错...");
        }
        User user = userService.findById(userId);
        user.setFace_image(uploadPathDB);
        userService.update(user);
        return JSONResult.ok(uploadPathDB);
    }


    @ApiOperation(value = "查询用户信息",notes = "查询用户信息接口")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,
            dataType = "int",paramType = "query")
    @PostMapping("/query")
    public JSONResult query(Integer userId,Integer publisherId){
        if (userId == null){
            return JSONResult.errorMsg("用户userId不能为空！");
        }
        User user = userService.findById(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        UsersFans usersFans = new UsersFans();
        usersFans.setUser_id(publisherId);
        usersFans.setFan_id(userId);
        Integer follow = userService.isFollow(usersFans);
        userVO.setFollow(follow);
        return JSONResult.ok(userVO);
    }

    @PostMapping("/queryPublisher")
    public JSONResult queryPublisher(Integer loginUserId, Integer videoId,
                                          Integer publishUserId) {

        if (publishUserId == null) {
            return JSONResult.errorMsg("");
        }

        // 1. 查询视频发布者的信息
        User userInfo = userService.findById(publishUserId);
        UserVO publisher = new UserVO();
        BeanUtils.copyProperties(userInfo, publisher);
        UsersFans usersFans = new UsersFans();
        usersFans.setUser_id(loginUserId);
        usersFans.setFan_id(publishUserId);
        userService.isFollow(usersFans);
        Integer follow = userService.isFollow(usersFans);
        publisher.setFollow(follow);

        // 2. 查询当前登录者和视频的点赞关系
        boolean userLikeVideo = userService.isUserLikeVideo(loginUserId, videoId);

        PublisherVideo bean = new PublisherVideo();
        bean.setPublisher(publisher);
        bean.setUserLikeVideo(userLikeVideo);

        return JSONResult.ok(bean);
    }

    @PostMapping("/beyourfans")
    public JSONResult beyourfans(Integer userId, Integer fanId) {

        if (userId == null || fanId == null) {
            return JSONResult.errorMsg("");
        }
        userService.saveUserFanRelation(userId, fanId);
        return JSONResult.ok("关注成功...");
    }

    @PostMapping("/dontbeyourfans")
    public JSONResult dontbeyourfans(Integer userId, Integer fanId) {

        if (userId == null || fanId == null) {
            return JSONResult.errorMsg("");
        }

        userService.dontbeyourfans(userId, fanId);

        return JSONResult.ok("取消关注成功...");
    }

    @PostMapping("/reportUser")
    public JSONResult reportUser(@RequestBody UsersReport usersReport) {

        // 保存举报信息
        userService.reportUser(usersReport);

        return JSONResult.errorMsg("举报成功...有你平台变得更美好...");
    }


}
