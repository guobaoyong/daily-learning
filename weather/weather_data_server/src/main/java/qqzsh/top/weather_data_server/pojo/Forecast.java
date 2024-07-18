package qqzsh.top.weather_data_server.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 16:30
 * @description 未来天气
 */
@Data
public class Forecast implements Serializable {
    private static final long serialVersionUID = 1L;

    private String date;
    private String high;
    private String fengli;
    private String low;
    private String fengxiang;
    private String type;

}
