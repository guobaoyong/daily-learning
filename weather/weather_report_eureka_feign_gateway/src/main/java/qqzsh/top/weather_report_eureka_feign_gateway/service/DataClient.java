package qqzsh.top.weather_report_eureka_feign_gateway.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import qqzsh.top.weather_report_eureka_feign_gateway.pojo.City;
import qqzsh.top.weather_report_eureka_feign_gateway.pojo.WeatherResponse;

import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-06 19:25
 * @description
 */
@FeignClient("weather-eureka-client-zuul")
public interface DataClient {

    @GetMapping("/city/cities")
    List<City> listCity() throws Exception;

    @GetMapping("/data/weather/cityId/{cityId}")
    WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId);

}
