package qqzsh.top.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import qqzsh.top.weather.service.CityDataService;
import qqzsh.top.weather.service.WeatherReportService;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:54
 * @description
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherReportService weatherReportService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getWeatherByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        model.addAttribute("title", "天气预报Demo");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityDataService.listCity());
        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("report", "reportModel", model);
    }
}
