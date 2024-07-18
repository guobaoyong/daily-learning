package qqzsh.top.hd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-30 21:00
 * @description
 */
@Data
@TableName("users_fans")
public class UsersFans {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer user_id;
    private Integer fan_id;
}
