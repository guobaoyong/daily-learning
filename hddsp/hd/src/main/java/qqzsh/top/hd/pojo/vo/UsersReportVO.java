package qqzsh.top.hd.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-31 17:36
 * @description
 */
@Data
public class UsersReportVO {

    private Integer id;
    //被举报用户
    private Integer deal_user_id;
    //被举报用户的账号
    private String deal_user;
    //被举报视频id
    private Integer deal_video_id;
    //举报类型
    private String title;
    //举报内容
    private String content;
    //举报用户id
    private String userid;
    //视频创建时间
    private Date create_date;
    //处理状态
    private Integer status;
    //被举报视频地址
    private String path;
    //举报用户账号
    private String touser;
}
