package qqzsh.top.hd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-31 11:24
 * @description
 */
@Data
@TableName("comments")
public class Comments {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer father_comment_id;
    private Integer to_user_id;
    private Integer video_id;
    private Integer from_user_id;
    private String comment;
    private Date create_time;
}
