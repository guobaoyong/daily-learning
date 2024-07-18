package qqzsh.top.weather_city_eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.weather_city_eureka.pojo.City;
import qqzsh.top.weather_city_eureka.service.CityDataService;

import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-06 15:53
 * @description
 */
@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityDataService cityDataService;

    @GetMapping
    public List<City> listCity() throws Exception {
        return cityDataService.listCity();
    }
}
