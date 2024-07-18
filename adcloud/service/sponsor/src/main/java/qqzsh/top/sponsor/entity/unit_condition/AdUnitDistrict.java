package qqzsh.top.sponsor.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 10:38
 * @Description 推广单元维度-地域维度
 * 依赖推广单元
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit_district")
public class AdUnitDistrict {

    //自增主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //推广单元 id
    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    //省
    @Basic
    @Column(name = "province", nullable = false)
    private String province;

    //市
    @Basic
    @Column(name = "city", nullable = false)
    private String city;

    public AdUnitDistrict(Long unitId, String province, String city) {
        this.unitId = unitId;
        this.province = province;
        this.city = city;
    }
}

