package qqzsh.top.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 13:29
 * @Description 推广计划请求VO
 * 新增/修改/删除使用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanRequest {
    //推广计划ID
    private Long id;
    //所属用户ID
    private Long userId;
    //推广计划名称
    private String planName;
    //开始时间
    private String startDate;
    //结束时间
    private String endDate;

    public boolean createValidate() {
        return userId != null
                && !StringUtils.isEmpty(planName)
                && !StringUtils.isEmpty(startDate)
                && !StringUtils.isEmpty(endDate);
    }

    public boolean updateValidate() {
        return id != null && userId != null;
    }

    public boolean deleteValidate() {
        return id != null && userId != null;
    }
}

