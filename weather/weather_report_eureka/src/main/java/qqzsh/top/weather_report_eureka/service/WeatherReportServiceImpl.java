package qqzsh.top.weather_report_eureka.service;

import org.springframework.stereotype.Service;
import qqzsh.top.weather_report_eureka.pojo.Forecast;
import qqzsh.top.weather_report_eureka.pojo.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:53
 * @description
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Override
    public Weather getDataByCityId(String cityId) {
        //TODO 改为由天气数据API微服务来提供
        Weather data = new Weather();
        data.setAqi("81");
        data.setCity("深圳");
        data.setGanmao("容易感冒，多穿衣服！");
        data.setWendu("22");

        List<Forecast> forecastList = new ArrayList<>();
        Forecast forecast = new Forecast();
        forecast.setDate("27日星期六");
        forecast.setType("晴");
        forecast.setFengxiang("无风");
        forecast.setHigh("高温11度");
        forecast.setLow("低温1度");

        forecastList.add(forecast);

        forecast = new Forecast();
        forecast.setDate("28日星期日");
        forecast.setType("晴");
        forecast.setFengxiang("无风");
        forecast.setHigh("高温11度");
        forecast.setLow("低温1度");

        forecastList.add(forecast);

        data.setForecast(forecastList);
        return data;
    }
}
