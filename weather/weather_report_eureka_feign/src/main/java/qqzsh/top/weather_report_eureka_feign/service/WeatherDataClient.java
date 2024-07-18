package qqzsh.top.weather_report_eureka_feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import qqzsh.top.weather_report_eureka_feign.pojo.WeatherResponse;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-06 19:25
 * @description
 */
@FeignClient("weather-data-eureka")
public interface WeatherDataClient {

    @GetMapping("/weather/cityId/{cityId}")
    WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId);

}
