package qqzsh.top.sponsor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import qqzsh.top.sponsor.entity.AdUnit;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 11:03
 * @Description 推广单元Repository
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {

    //根据推广计划id和推广单元名称查询
    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    //根据推广单元状态查询
    List<AdUnit> findAllByUnitStatus(Integer unitStatus);
}

