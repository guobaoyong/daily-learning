package qqzsh.top.weather_report_eureka_feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import qqzsh.top.weather_report_eureka_feign.pojo.City;

import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-06 19:25
 * @description
 */
@FeignClient("weather-city-eureka")
public interface CityClient {

    @GetMapping("/cities")
    List<City> listCity() throws Exception;
}
