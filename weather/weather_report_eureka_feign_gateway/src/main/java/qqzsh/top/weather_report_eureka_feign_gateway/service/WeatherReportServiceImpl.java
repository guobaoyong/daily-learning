package qqzsh.top.weather_report_eureka_feign_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqzsh.top.weather_report_eureka_feign_gateway.pojo.Weather;
import qqzsh.top.weather_report_eureka_feign_gateway.pojo.WeatherResponse;


/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:53
 * @description
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private DataClient dataClient;

    @Override
    public Weather getDataByCityId(String cityId) {
        //由天气数据API微服务来提供
        WeatherResponse response = dataClient.getDataByCityId(cityId);
        Weather data = null;
        if (response != null) {
            data = response.getData();
        }
        return data;
    }
}
