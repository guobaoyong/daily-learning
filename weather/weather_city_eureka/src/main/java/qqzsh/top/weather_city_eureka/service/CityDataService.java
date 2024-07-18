package qqzsh.top.weather_city_eureka.service;

import qqzsh.top.weather_city_eureka.pojo.City;

import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-06 15:54
 * @description
 */
public interface CityDataService {

    /**
     * 获取City列表
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
