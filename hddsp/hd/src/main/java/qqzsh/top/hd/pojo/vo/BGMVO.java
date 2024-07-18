package qqzsh.top.hd.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-29 17:29
 * @description
 */
@Data
public class BGMVO {
    private Integer id;
    private String author;
    private String name;
    private String path;
    private Integer user_id;
    private String nickname;
}
