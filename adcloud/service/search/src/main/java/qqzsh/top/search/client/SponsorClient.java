package qqzsh.top.search.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qqzsh.top.common.vo.CommonResponse;
import qqzsh.top.search.client.vo.AdPlan;
import qqzsh.top.search.client.vo.AdPlanGetRequest;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 14:30
 * @Description
 */
@FeignClient(value = "eureka-client-sponsor",
        fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    @RequestMapping(value = "/sponsor/get/adPlan",
            method = RequestMethod.POST)
    CommonResponse<List<AdPlan>> getAdPlans(
            @RequestBody AdPlanGetRequest request);
}
