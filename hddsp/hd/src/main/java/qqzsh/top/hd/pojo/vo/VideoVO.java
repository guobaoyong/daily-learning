package qqzsh.top.hd.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-29 17:55
 * @description
 */
@Data
@TableName("videos")
@ApiModel(value = "视频对象",description = "这是视频对象")
public class VideoVO {

    /*@TableId(type = IdType.AUTO)*/
    @ApiModelProperty(hidden = true)
    private Integer id;
    @ApiModelProperty(value = "用户id",name = "user_id",required = true)
    private Integer user_id;
    @ApiModelProperty(value = "背景音乐id",name = "audio_id",required = false)
    private Integer audio_id;
    @ApiModelProperty(value = "视频描述",name = "video_desc",required = false)
    private String video_desc;
    @ApiModelProperty(hidden = true)
    private String video_path;
    @ApiModelProperty(value = "视频秒数",name = "video_seconds",required = true)
    private Float video_seconds;
    @ApiModelProperty(value = "视频宽度",name = "video_width",required = true)
    private Integer video_width;
    @ApiModelProperty(value = "视频高度",name = "video_height",required = true)
    private Integer video_height;
    @ApiModelProperty(hidden = true)
    private String cover_path;
    @ApiModelProperty(hidden = true)
    private Long like_counts;
    @ApiModelProperty(hidden = true)
    private Integer status;
    @ApiModelProperty(hidden = true)
    private Date create_time;
    private String face_image;
    private String nickname;
}
