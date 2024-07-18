package qqzsh.top.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 13:31
 * @Description 推广计划响应VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanResponse {
    //推广计划ID
    private Long id;
    //推广计划名称
    private String planName;
}
