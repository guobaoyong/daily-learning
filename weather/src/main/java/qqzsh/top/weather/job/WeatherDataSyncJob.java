package qqzsh.top.weather.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;
import qqzsh.top.weather.pojo.City;
import qqzsh.top.weather.service.CityDataService;
import qqzsh.top.weather.service.WeatherDataService;

import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:17
 * @description
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);
    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("天气数据同步 Job Start!");
        List<City> cityList = null;
        try {
            cityList = cityDataService.listCity();
        } catch (Exception e) {
            logger.error("Exception!",e);
        }

        if (CollectionUtils.isEmpty(cityList)) {
            logger.error("CityList is empty , check your operation");
            return;
        }
        //遍历城市ID获取天气
        for (City city : cityList) {
            String cityId = city.getCityId();
            logger.info("天气数据同步，cityId：" + cityId);

            weatherDataService.syncDateByCityId(cityId);
        }
        logger.info("天气数据同步 Job End!");

    }
}
