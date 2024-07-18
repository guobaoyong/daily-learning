package qqzsh.top.weather_eureka_client_feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.weather_eureka_client_feign.service.CityClient;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-06 18:50
 * @description
 */
@RestController
public class CityController {

    @Autowired
    private CityClient cityClient;

    @GetMapping("/cities")
    public String listCity() {
        String body = cityClient.listCity();
        return body;
    }
}
