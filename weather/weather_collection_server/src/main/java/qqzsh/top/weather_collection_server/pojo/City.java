package qqzsh.top.weather_collection_server.pojo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:19
 * @description
 */
@Data
public class City {

    private String cityId;

    private String cityName;

    private String cityCode;

    private String province;
}
