package qqzsh.top.soufun.web.form;

import javax.validation.constraints.NotNull;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-06 17:51
 * @Description 地铁/站点添加前台传输
 */
public class SubwayForm {
    private Long id;

    @NotNull(message = "城市名不允许为空!")
    private String city;

    @NotNull(message = "线路名不允许为空!")
    private String subway;

    @NotNull(message = "站点名不允许为空!")
    private String station;

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

    public String getSubway() {
        return subway;
    }

    public void setSubway(String subway) {
        this.subway = subway;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "SubwayForm{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", subway='" + subway + '\'' +
                ", station='" + station + '\'' +
                '}';
    }
}
