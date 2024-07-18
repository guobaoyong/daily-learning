package qqzsh.top.weather.pojo;

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
@XmlRootElement(name = "d")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class City {

    @XmlAttribute(name = "d1")
    private String cityId;

    @XmlAttribute(name = "d2")
    private String cityName;

    @XmlAttribute(name = "d3")
    private String cityCode;

    @XmlAttribute(name = "d4")
    private String province;
}
