package qqzsh.top.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqzsh.top.weather.pojo.Weather;
import qqzsh.top.weather.pojo.WeatherResponse;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:53
 * @description
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
        return resp.getData();
    }
}
