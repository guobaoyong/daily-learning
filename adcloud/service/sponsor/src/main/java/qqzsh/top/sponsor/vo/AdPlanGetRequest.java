package qqzsh.top.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 13:32
 * @Description 获取推广计划请求VO
 * 查询使用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRequest {
    //所属用户ID
    private Long userId;
    //批量获取，传递多个推广计划id
    private List<Long> ids;

    public boolean validate() {
        return userId != null && !CollectionUtils.isEmpty(ids);
    }
}
