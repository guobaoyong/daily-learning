package qqzsh.top.weather_city_server.pojo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-04 17:20
 * @description
 */
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CityList {

    @XmlElement(name = "d")
    private List<City> cityList;
}
