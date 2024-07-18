package qqzsh.top.weather_collection_eureka_feign.service;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-06 19:12
 * @description
 */
public interface WeatherDataCollectionService {

    /**
     * 根据城市ID同步天气
     * @param cityId
     */
    void syncDateByCityId(String cityId);
}
