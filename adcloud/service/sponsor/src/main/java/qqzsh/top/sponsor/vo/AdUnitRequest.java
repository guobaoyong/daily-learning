package qqzsh.top.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 13:51
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;

    private Integer positionType;
    private Long budget;

    public boolean createValidate() {
        return null != planId && !StringUtils.isEmpty(unitName)
                && positionType != null && budget != null;
    }
}
