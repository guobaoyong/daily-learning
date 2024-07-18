package qqzsh.top.hd.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-06-01 9:32
 * @description
 */
@Data
public class CommentsVO {

    private Integer id;

    /**
     * 视频id
     */
    private Integer video_id;

    /**
     * 留言者，评论的用户id
     */
    private Integer from_user_id;

    private Date create_time;

    /**
     * 评论内容
     */
    private String comment;

    private String face_image;
    private String nickname;
    private String toNickname;
    private String timeAgoStr;
}
