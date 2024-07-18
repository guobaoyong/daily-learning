package qqzsh.top.sponsor.service;

import qqzsh.top.common.exception.AdException;
import qqzsh.top.sponsor.entity.AdPlan;
import qqzsh.top.sponsor.vo.AdPlanGetRequest;
import qqzsh.top.sponsor.vo.AdPlanRequest;
import qqzsh.top.sponsor.vo.AdPlanResponse;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 13:28
 * @Description 推广计划接口
 */
public interface IAdPlanService {

    //创建推广计划
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    //获取推广计划
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    //更新推广计划
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    //删除推广计划
    void deleteAdPlan(AdPlanRequest request) throws AdException;

}
