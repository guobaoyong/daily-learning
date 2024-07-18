package qqzsh.top.contentcenter.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 19:17
 * @Description
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}

