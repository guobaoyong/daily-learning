package qqzsh.top.hd.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-29 9:04
 * @description
 */
@Data
@ApiModel(value = "用户对象",description = "这是用户对象")
public class UserVO {
    @ApiModelProperty(hidden = true)
    private Integer id;
    @ApiModelProperty(hidden = true)
    private String userToken;
    @ApiModelProperty(value = "用户名",name = "username",example = "zsh",required = true)
    private String username;
    @ApiModelProperty(value = "密码",name = "password",example = "000",required = true)
    @JsonIgnore
    private String password;
    @ApiModelProperty(hidden = true)
    private String face_image;
    @ApiModelProperty(hidden = true)
    private String nickname;
    @ApiModelProperty(hidden = true)
    private Integer fans_counts;
    @ApiModelProperty(hidden = true)
    private Integer follow_counts;
    @ApiModelProperty(hidden = true)
    private Integer receive_like_counts;
    private Integer follow;
    private Integer role;

}
