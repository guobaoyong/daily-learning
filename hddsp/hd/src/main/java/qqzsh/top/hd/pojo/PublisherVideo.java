package qqzsh.top.hd.pojo;

import lombok.Data;
import qqzsh.top.hd.pojo.vo.UserVO;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-30 17:31
 * @description
 */
@Data
public class PublisherVideo {
    public UserVO publisher;
    public boolean userLikeVideo;
}
