package qqzsh.top.weather_report_server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import qqzsh.top.weather_report_server.pojo.City;
import qqzsh.top.weather_report_server.service.WeatherReportService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:54
 * @description
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {
    private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

    @Autowired
    private WeatherReportService weatherReportService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getWeatherByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        //TODO 改为由城市数据API微服务来提供数据
        List<City> cityList = null;
        try {
            //TODO 改为城市数据API微服务来提供数据
            cityList = new ArrayList<>();
            City city = new City();
            city.setCityId("101280601");
            city.setCityName("深圳");
            cityList.add(city);
        } catch (Exception e) {
            logger.error("Exception!",e);
        }
        model.addAttribute("cityList", cityList);

        model.addAttribute("title", "天气预报Demo");
        model.addAttribute("cityId", cityId);
        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("report", "reportModel", model);
    }
}
