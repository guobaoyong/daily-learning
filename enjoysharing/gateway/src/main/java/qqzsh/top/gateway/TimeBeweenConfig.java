package qqzsh.top.gateway;

import lombok.Data;

import java.time.LocalTime;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 17:15
 * @Description
 */
@Data
public class TimeBeweenConfig {
    private LocalTime start;
    private LocalTime end;
}
