package qqzsh.top.hd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.hd.utils.RedisOperator;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-29 8:58
 * @description Controller的常量
 */
@RestController
public class BasicController {

    //Redis
    @Autowired
    public RedisOperator redisOperator;

    public static final String USER_REDIS_SESSION = "user-redis-session";

    // 文件保存的命名空间
    public static final String FILE_SPACE = "res/";

    // ffmpeg所在目录
    public static final String FFMPEG_EXE = "D:\\ffmpeg\\bin\\ffmpeg.exe";
    public static final String FFMPEG = "ffmpeg";

}
