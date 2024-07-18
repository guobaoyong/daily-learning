package qqzsh.top.weather_data_eureka.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 16:29
 * @description
 */
@Data
public class Yesterday implements Serializable {
    private static final long serialVersionUID = 1L;

    private  String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;
}
