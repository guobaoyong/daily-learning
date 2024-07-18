package qqzsh.top.weather_eureka_client_feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-06 18:51
 * @description
 */
@FeignClient("weather-city-eureka")
public interface CityClient {

    @GetMapping("/cities")
    String listCity();
}
