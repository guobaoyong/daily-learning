package qqzsh.top.weather_collection_server.service;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-06 10:01
 * @description
 */
public interface WeatherDataCollectionService {
    /**
     * 根据城市ID同步天气
     * @param cityId
     */
    void syncDateByCityId(String cityId);
}
