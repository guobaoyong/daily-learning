package qqzsh.top.dianping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 21:32
 * @Description 商户
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("seller")
public class Seller {

    //主键id
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    //商户名称
    @TableField("name")
    private String name;

    //创建时间
    @TableField("created_at")
    private LocalDateTime createdAt;

    //更新时间
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    //评分
    @TableField("remark_score")
    private BigDecimal remarkScore;

    //启用标志
    @TableField("disabled_flag")
    private Integer disabledFlag;

}
