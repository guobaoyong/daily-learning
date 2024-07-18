package qqzsh.top.weather_collection_eureka.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qqzsh.top.weather_collection_eureka.job.WeatherDataSyncJob;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:26
 * @description
 */
@Configuration
public class QuartzConfiguration {

    private  static final int TIME = 1800;//更新频率
    //JobDetail 用于定义一个特定的一个Job
    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob")
                .storeDurably().build();
    }

    //Trigger 定义的Job何时触发
    @Bean
    public Trigger weatherDataSyncTrigger() {
        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(TIME).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
                .withIdentity("weatherDataSyncJobDetail").withSchedule(schedBuilder).build();
    }
}
