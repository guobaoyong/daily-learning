package qqzsh.top.soufun.service.search;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-04 8:41
 * @Description 百度位置信息
 */
public class BaiduMapLocation {
    // 经度
    @JsonProperty("lon")
    private double longitude;

    // 纬度
    @JsonProperty("lat")
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

