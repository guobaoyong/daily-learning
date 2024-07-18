package qqzsh.top.sponsor.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qqzsh.top.common.exception.AdException;
import qqzsh.top.sponsor.SponsorApplicationTests;
import qqzsh.top.sponsor.vo.AdPlanGetRequest;

import java.util.Collections;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 18:21
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SponsorApplicationTests.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdPlanServiceTest {

    @Autowired
    private IAdPlanService planService;

    @Test
    public void testGetAdPlan() throws AdException {

        System.out.println(
                planService.getAdPlanByIds(
                        new AdPlanGetRequest(15L, Collections.singletonList(10L))
                )
        );
    }
}
