package qqzsh.top.hd.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qqzsh.top.hd.pojo.*;
import qqzsh.top.hd.pojo.vo.CommentsVO;
import qqzsh.top.hd.pojo.vo.VideoVO;
import qqzsh.top.hd.service.BGMService;
import qqzsh.top.hd.service.CommentsService;
import qqzsh.top.hd.service.SearchRecordService;
import qqzsh.top.hd.service.VideoService;
import qqzsh.top.hd.utils.FetchVideoCover;
import qqzsh.top.hd.utils.JSONResult;
import qqzsh.top.hd.utils.MergeVideoMp3;
import qqzsh.top.hd.utils.TimeAgoUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:04
 * @description
 */
@RestController
@Api(value = "视频业务接口",tags = {"视频业务的controller"})
@RequestMapping("/video")
public class VideoController extends BasicController{

    @Autowired
    private VideoService videoService;

    @Autowired
    private BGMService bgmService;

    @Autowired
    private SearchRecordService searchRecordService;

    @Autowired
    private CommentsService commentsService;

    @ApiOperation(value = "上传视频",notes = "上传视频接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,
                    dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "bgmId",value = "背景音乐id",required = false,
                    dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "videoSeconds",value = "视频长度",required = true,
                    dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "videoWidth",value = "视频宽度",required = true,
                    dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "videoHeight",value = "视频高度",required = true,
                    dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "desc",value = "视频描述",required = false,
                    dataType = "String",paramType = "form")
    })
    @PostMapping(value = "/upload",headers = "content-type=multipart/form-data")
    public JSONResult upload(Integer userId,Integer bgmId,
                                 double videoSeconds,Integer videoWidth,
                                 Integer videoHeight,String desc,
                             @ApiParam(value = "短视频",required = true)
                             MultipartFile file) throws Exception {
        if (userId == null){
            return JSONResult.errorMsg("用户userId不能为空！");
        }
        //文件保存的命名空间
        String fileSpace = "res";
        //保存到数据库的路径
        String uploadPathDB = "/" + userId + "/video";
        String coverPathDB = "/" + userId + "/video";
        String finalVideoPath = "";
        if (file != null){
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            String filename = file.getOriginalFilename();
            String arrayFilenameItem[] =  filename.split("\\.");
            String fileNamePrefix = "";
            for (int i = 0 ; i < arrayFilenameItem.length-1 ; i ++) {
                fileNamePrefix += arrayFilenameItem[i];
            }
            try {
                if (StringUtils.isNotBlank(filename)){
                    //文件上传的最终路径
                    finalVideoPath = fileSpace + uploadPathDB + "/" + filename;
                    coverPathDB = coverPathDB + "/" + fileNamePrefix + ".jpg";
                    //设置数据库的保存路径
                    uploadPathDB += ("/" + filename);
                    File outfile = new File(finalVideoPath);
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
        }else {
            return JSONResult.errorMsg("上传出错...");
        }

        // 判断bgmId是否为空，如果不为空，
        // 那就查询bgm的信息，并且合并视频，生产新的视频
        if (bgmId != null) {
            BGM bgm = bgmService.queryBgmById(bgmId);
            String mp3InputPath = FILE_SPACE + bgm.getPath();

            MergeVideoMp3 tool = new MergeVideoMp3(FFMPEG_EXE);
            if (System.getProperty("os.name").contains("Linux")){
                tool = new MergeVideoMp3(FFMPEG);
            }
            String videoInputPath = finalVideoPath;

            String videoOutputName = UUID.randomUUID().toString() + ".mp4";
            uploadPathDB = "/" + userId + "/video" + "/" + videoOutputName;
            finalVideoPath = FILE_SPACE + uploadPathDB;
            tool.convertor(videoInputPath, mp3InputPath, videoSeconds, finalVideoPath);
        }

        // 对视频进行截图
        FetchVideoCover videoInfo = new FetchVideoCover(FFMPEG_EXE);
        if (System.getProperty("os.name").contains("Linux")){
            videoInfo = new FetchVideoCover(FFMPEG);
        }
        videoInfo.getCover(finalVideoPath, FILE_SPACE + coverPathDB);

        // 保存视频信息到数据库
        Video video = new Video();
        video.setAudio_id(bgmId);
        video.setUser_id(userId);
        video.setVideo_seconds((float)videoSeconds);
        video.setVideo_height(videoHeight);
        video.setVideo_width(videoWidth);
        video.setVideo_desc(desc);
        video.setVideo_path(uploadPathDB);
        video.setCover_path(coverPathDB);
        video.setLike_counts(0l);
        video.setStatus(VideoStatusEnum.SUCCESS.value);
        video.setCreate_time(new Date());

        Integer videoId = videoService.insert(video);
        return JSONResult.ok(videoId);
    }

    @ApiOperation(value="上传封面", notes="上传封面的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId", value="用户id", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoId", value="视频主键id", required=true,
                    dataType="String", paramType="form")
    })
    @PostMapping(value="/uploadCover", headers="content-type=multipart/form-data")
    public JSONResult uploadCover(Integer userId,
                                       Integer videoId,
                                       @ApiParam(value="视频封面", required=true)
                                               MultipartFile file) throws Exception {

        if (videoId == null
                || userId == null) {
            return JSONResult.errorMsg("视频主键id和用户id不能为空...");
        }

        // 文件保存的命名空间
//		String fileSpace = "C:/imooc_videos_dev";
        // 保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        // 文件上传的最终保存路径
        String finalCoverPath = "";
        try {
            if (file != null) {

                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {

                    finalCoverPath = FILE_SPACE + uploadPathDB + "/" + fileName;
                    // 设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalCoverPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        // 创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }

            } else {
                return JSONResult.errorMsg("上传出错...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传出错...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        videoService.update(videoId, uploadPathDB);

        return JSONResult.ok();
    }

    @PostMapping("/showAll")
    public JSONResult showAll(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                              @RequestBody Video video,
                              @RequestParam(name = "isSaveRecord", defaultValue = "0") Integer isSaveRecord){
        Page<VideoVO> page = new Page<>(pageNum,pageSize);
        List<VideoVO> videoVOS = videoService.queryVideo(video,isSaveRecord,page);
        page.setRecords(videoVOS);
        return JSONResult.ok(page);
    }

    @PostMapping("/showOwn")
    public JSONResult showOwn(int pageNum,
                              @RequestParam(name = "pageSize", defaultValue = "6")int pageSize,
                              int userId){
        Page<VideoVO> page = new Page<>(pageNum,pageSize);
        List<VideoVO> videoVOS = videoService.listOwn(page,userId);
        page.setRecords(videoVOS);
        return JSONResult.ok(page);
    }


    /**
     * 得到搜索热词（前12个）
     * @return
     */
    @PostMapping("/hot")
    public JSONResult hot(){
        return JSONResult.ok(searchRecordService.getHotwords());
    }

    @PostMapping(value="/userLike")
    public JSONResult userLike(Integer userId, Integer videoId, Integer videoCreaterId) {
        videoService.userLikeVideo(userId, videoId, videoCreaterId);
        return JSONResult.ok();
    }

    @PostMapping(value="/userUnLike")
    public JSONResult userUnLike(Integer userId, Integer videoId, Integer videoCreaterId) {
        videoService.userUnLikeVideo(userId, videoId, videoCreaterId);
        return JSONResult.ok();
    }

    @PostMapping("/showMyLike")
    public JSONResult showMyLike(int userId,int pageNum,
                              @RequestParam(name = "pageSize", defaultValue = "6")int pageSize){
        Page<VideoVO> page = new Page<>(pageNum,pageSize);
        List<VideoVO> videoVOS = videoService.queryMyshowMyLikeVideos(page,userId);
        page.setRecords(videoVOS);
        return JSONResult.ok(page);
    }

    @PostMapping("/showMyFollow")
    public JSONResult showMyFollow(int userId,int pageNum,
                                 @RequestParam(name = "pageSize", defaultValue = "6")int pageSize){
        Page<VideoVO> page = new Page<>(pageNum,pageSize);
        List<VideoVO> videoVOS = videoService.queryMyFollowVideos(page,userId);
        page.setRecords(videoVOS);
        return JSONResult.ok(page);
    }

    @PostMapping("saveComment")
    public JSONResult saveComment(@RequestBody Comments comments){
        System.out.println(comments);
        commentsService.insert(comments);
        return JSONResult.ok();
    }

    /**
     * 根据视频ID得到所有视频评论
     * @param videoId
     * @param page
     * @param pageSize
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoId",value = "视频id",required = true,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页码",required = true,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示条数",required = true,
                    dataType = "String",paramType = "query")
    })
    @PostMapping("/getVideoComments")
    public JSONResult getVideoComments(Integer videoId,Integer page,Integer pageSize){
        if (videoId == null){
            return JSONResult.ok();
        }
        Page<CommentsVO> pages = new Page<>(page,pageSize);
        List<CommentsVO> comments = videoService.getAllComments(videoId, pages);

        for (CommentsVO c : comments){
            c.setTimeAgoStr(TimeAgoUtils.format(c.getCreate_time()));
        }
        pages.setRecords(comments);
        return JSONResult.ok(pages);

    }
}
