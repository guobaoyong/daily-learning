package qqzsh.top.soufun.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-04 19:29
 * @Description 城市/区域添加前台传输
 */
public class CityForm {
    private Long id;

    @NotNull(message = "城市名不允许为空!")
    private String city;

    @NotNull(message = "区域名不允许为空!")
    private String region;

    private String belongTo;

    private String lng;

    private String lat;

    private String cnName;

    private String level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "CityForm{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", belongTo='" + belongTo + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", cnName='" + cnName + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
