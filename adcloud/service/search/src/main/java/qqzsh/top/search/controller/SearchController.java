package qqzsh.top.search.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import qqzsh.top.common.annotation.IgnoreResponseAdvice;
import qqzsh.top.common.vo.CommonResponse;
import qqzsh.top.search.client.SponsorClient;
import qqzsh.top.search.client.vo.AdPlan;
import qqzsh.top.search.client.vo.AdPlanGetRequest;
import qqzsh.top.search.service.ISearch;
import qqzsh.top.search.vo.SearchRequest;
import qqzsh.top.search.vo.SearchResponse;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 14:39
 * @Description
 */
@Slf4j
@RestController
public class SearchController {

    private final ISearch search;

    private final RestTemplate restTemplate;

    private final SponsorClient sponsorClient;

    @Autowired
    public SearchController(RestTemplate restTemplate,
                            SponsorClient sponsorClient, ISearch search) {
        this.restTemplate = restTemplate;
        this.sponsorClient = sponsorClient;
        this.search = search;
    }

    @PostMapping("/fetchAds")
    public SearchResponse fetchAds(@RequestBody SearchRequest request) {

        log.info("search: fetchAds -> {}",
                JSON.toJSONString(request));
        return search.fetchAds(request);
    }

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlans(
            @RequestBody AdPlanGetRequest request
    ) {
        log.info("search: getAdPlans -> {}",
                JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }

    @SuppressWarnings("all")
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlansByRibbon")
    public CommonResponse<List<AdPlan>> getAdPlansByRebbon(
            @RequestBody AdPlanGetRequest request
    ) {
        log.info("search: getAdPlansByRibbon -> {}",
                JSON.toJSONString(request));
        return restTemplate.postForEntity(
                "http://eureka-client-sponsor/sponsor/get/adPlan",
                request,
                CommonResponse.class
        ).getBody();
    }
}
