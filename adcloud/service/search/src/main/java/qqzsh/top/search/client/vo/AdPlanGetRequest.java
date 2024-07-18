package qqzsh.top.search.client.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 14:29
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRequest {
    private Long userId;
    private List<Long> ids;
}
