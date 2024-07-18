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
 * @create 2019-12-15 10:35
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("shop")
public class Shop {

    //主键id
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    //创建时间
    @TableField("created_at")
    private LocalDateTime createdAt;

    //更新时间
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    //门店名称
    @TableField("name")
    private String name;

    //评分
    @TableField("remark_score")
    private BigDecimal remarkScore;

    //人均消费
    @TableField("price_per_man")
    private Integer pricePerMan;

    //纬度
    @TableField("latitude")
    private BigDecimal latitude;

    //经度
    @TableField("longitude")
    private BigDecimal longitude;

    //距离
    @TableField(exist = false)
    private Integer distance;

    //品类ID
    @TableField("category_id")
    private Long categoryId;

    //品类
    @TableField(exist = false)
    private Category category;

    //标签
    @TableField("tags")
    private String tags;

    //营业开始时间
    @TableField("start_time")
    private String startTime;

    //营业结束时间
    @TableField("end_time")
    private String endTime;

    //地址
    @TableField("address")
    private String address;

    //商户ID
    @TableField("seller_id")
    private Long sellerId;

    //商户
    @TableField(exist = false)
    private Seller seller;

    //门店图片
    @TableField("icon_url")
    private String iconUrl;
}
