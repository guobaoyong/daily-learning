package qqzsh.top.weather_report_eureka_feign.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 16:30
 * @description
 */
@Data
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Weather data;
    private Integer status;
    private String desc;
}
