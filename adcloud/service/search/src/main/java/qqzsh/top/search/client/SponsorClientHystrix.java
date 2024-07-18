package qqzsh.top.search.client;

import org.springframework.stereotype.Component;
import qqzsh.top.common.vo.CommonResponse;
import qqzsh.top.search.client.vo.AdPlan;
import qqzsh.top.search.client.vo.AdPlanGetRequest;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 14:32
 * @Description 熔断器
 */
@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(
            AdPlanGetRequest request) {
        return new CommonResponse<>(-1,
                "eureka-client-sponsor error");
    }
}

