package qqzsh.top.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 11:13
 * @Description 创建用户请求VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    //用户名
    private String username;

    public boolean validate() {
        return !StringUtils.isEmpty(username);
    }
}
