package qqzsh.top.sponsor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.common.exception.AdException;
import qqzsh.top.sponsor.constant.CommonStatus;
import qqzsh.top.sponsor.constant.Constants;
import qqzsh.top.sponsor.dao.AdPlanRepository;
import qqzsh.top.sponsor.dao.AdUserRepository;
import qqzsh.top.sponsor.entity.AdPlan;
import qqzsh.top.sponsor.entity.AdUser;
import qqzsh.top.sponsor.service.IAdPlanService;
import qqzsh.top.sponsor.utils.CommonUtils;
import qqzsh.top.sponsor.vo.AdPlanGetRequest;
import qqzsh.top.sponsor.vo.AdPlanRequest;
import qqzsh.top.sponsor.vo.AdPlanResponse;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 13:37
 * @Description 推广计划实现类
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserRepository userRepository;
    private final AdPlanRepository planRepository;

    //获取dao层接口
    @Autowired
    public AdPlanServiceImpl(AdUserRepository userRepository,
                             AdPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    //创建推广计划
    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request)
            throws AdException {
        //请求参数是否合法
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        // 确保关联的 User 是存在的 jdk8新方法，处理Null
        Optional<AdUser> adUser =
                userRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        //查重，判断是否存在同名推广计划
        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(
                request.getUserId(), request.getPlanName()
        );
        if (oldPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        //调用dao层方法保存数据库
        AdPlan newAdPlan = planRepository.save(
                new AdPlan(request.getUserId(), request.getPlanName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate())
                )
        );
        //返回前台响应
        return new AdPlanResponse(newAdPlan.getId(),
                newAdPlan.getPlanName());
    }

    //获取推广计划
    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request)
            throws AdException {
        //判断请求参数是否合法
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        //返回前台结果集
        return planRepository.findAllByIdInAndUserId(
                request.getIds(), request.getUserId()
        );
    }

    //更新推广计划
    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request)
            throws AdException {
        //判断请求参数是否合法
        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        //通过id和userId获取信息
        AdPlan plan = planRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        //校验推广计划名称，开始时间，结束时间是否存在
        if (request.getPlanName() != null) {
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null) {
            plan.setStartDate(
                    CommonUtils.parseStringDate(request.getStartDate())
            );
        }
        if (request.getEndDate() != null) {
            plan.setEndDate(
                    CommonUtils.parseStringDate(request.getEndDate())
            );
        }
        //保存更新时间
        plan.setUpdateTime(new Date());
        //保存到数据库
        plan = planRepository.save(plan);
        //返回前台响应
        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    //删除推广计划
    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        //判断请求参数是否合法
        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        //先查询推广计划是否存在
        AdPlan plan = planRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        //更新状态为无效状态
        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        //修改最后更新时间
        plan.setUpdateTime(new Date());
        //保存数据库
        planRepository.save(plan);
    }
}

