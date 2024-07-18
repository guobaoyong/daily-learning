package qqzsh.top.sponsor.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 10:36
 * @Description 推广单元维度-兴趣维度
 * 依赖推广单元
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit_it")
public class AdUnitIt {

    //自增主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //推广单元 id
    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    //兴趣标签(名称)
    @Basic
    @Column(name = "it_tag", nullable = false)
    private String itTag;

    public AdUnitIt(Long unitId, String itTag) {
        this.unitId = unitId;
        this.itTag = itTag;
    }
}

