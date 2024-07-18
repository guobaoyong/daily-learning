package qqzsh.top.dianping.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 23:40
 * @Description 品类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("category")
public class Category {

    //主键id
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    //创建时间
    @TableField("created_at")
    private LocalDateTime createdAt;

    //更新时间
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    //品类名称
    @TableField("name")
    private String name;

    //品类图地址
    @TableField("icon_url")
    private String iconUrl;

    //排序
    @TableField("sort")
    private Integer sort;
}
