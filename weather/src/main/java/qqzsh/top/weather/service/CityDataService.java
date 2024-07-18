package qqzsh.top.weather.service;

import qqzsh.top.weather.pojo.City;

import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:22
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
