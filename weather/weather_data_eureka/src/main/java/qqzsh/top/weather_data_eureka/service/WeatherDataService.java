package qqzsh.top.weather_data_eureka.service;

import qqzsh.top.weather_data_eureka.pojo.WeatherResponse;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 16:33
 * @description
 */
public interface WeatherDataService {

    /**
     * 根据城市ID查询天气数据
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气数据
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);
}
