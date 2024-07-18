package qqzsh.top.soufun.web.dto;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-02 18:56
 * @Description
 */
public class SubwayDTO {
    private Long id;
    private String name;
    private String cityEnName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }
}
