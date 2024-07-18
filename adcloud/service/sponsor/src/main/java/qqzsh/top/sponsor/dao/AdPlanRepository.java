package qqzsh.top.sponsor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import qqzsh.top.sponsor.entity.AdPlan;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 11:02
 * @Description 推广计划Repository
 */
public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

    //根据userId查询
    AdPlan findByIdAndUserId(Long id, Long userId);

    //根据多个id查询
    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);

    //根据推广计划名称查询
    AdPlan findByUserIdAndPlanName(Long userId, String planName);

    //根据状态查询
    List<AdPlan> findAllByPlanStatus(Integer status);
}
