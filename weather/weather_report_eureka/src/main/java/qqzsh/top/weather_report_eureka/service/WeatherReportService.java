package qqzsh.top.weather_report_eureka.service;

import qqzsh.top.weather_report_eureka.pojo.Weather;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:53
 * @description
 */
public interface WeatherReportService {

    /**
     * 根据城市ID查询天气信息
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);
}
