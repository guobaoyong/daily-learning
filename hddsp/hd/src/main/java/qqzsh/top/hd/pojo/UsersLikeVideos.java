package qqzsh.top.hd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-30 17:35
 * @description
 */
@Data
@TableName("users_like_videos")
public class UsersLikeVideos {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer user_id;
    private Integer video_id;
}
