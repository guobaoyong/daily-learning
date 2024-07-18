package qqzsh.top.hd.pojo;

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
@TableName("users_report")
public class UsersReport {

    private Integer id;
    private Integer deal_user_id;
    private Integer deal_video_id;
    private String title;
    private String content;
    private String userid;
    private Date create_date;
    private Integer status;
}
