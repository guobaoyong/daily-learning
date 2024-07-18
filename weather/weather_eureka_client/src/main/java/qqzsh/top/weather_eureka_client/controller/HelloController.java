package qqzsh.top.weather_eureka_client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-07 8:50
 * @description
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}
