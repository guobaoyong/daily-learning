package qqzsh.top.addressbook.controller;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.addressbook.constant.SystemConstant;
import qqzsh.top.addressbook.entity.R;
import qqzsh.top.addressbook.util.JwtUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-16 17:26
 * @Description 刷新token
 */
@RestController
@Slf4j
public class TokenController {

    /**
     * 刷新用户token
     * @param request
     * @return
     */
    @GetMapping(value = "/refreshToken")
    public R refreshToken(HttpServletRequest request){
        Claims claims = JwtUtils.validateJWT(request.getHeader("token")).getClaims();
        String newToken=JwtUtils.createJWT(claims.getId(),claims.getSubject(), SystemConstant.JWT_TTL);
        R r=new R();
        r.put("token",newToken);
        log.info("新token:"+newToken);
        return r;
    }

}

