package qqzsh.top.search.search;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qqzsh.top.search.SearchApplicationTests;
import qqzsh.top.search.service.ISearch;
import qqzsh.top.search.vo.SearchRequest;
import qqzsh.top.search.vo.feature.DistrictFeature;
import qqzsh.top.search.vo.feature.FeatureRelation;
import qqzsh.top.search.vo.feature.ItFeature;
import qqzsh.top.search.vo.feature.KeywordFeature;
import qqzsh.top.search.vo.media.AdSlot;
import qqzsh.top.search.vo.media.App;
import qqzsh.top.search.vo.media.Device;
import qqzsh.top.search.vo.media.Geo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 18:25
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SearchApplicationTests.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SearchTest {

    @Autowired
    private ISearch search;

    @Test
    public void testFetchAds() {

        SearchRequest request = new SearchRequest();
        request.setMediaId("adcloud");

        // 第一个测试条件
        request.setRequestInfo(new SearchRequest.RequestInfo(
                "aaa",
                Collections.singletonList(new AdSlot(
                        "ad-x", 1,
                        1080, 720, Arrays.asList(1, 2),
                        1000
                )),
                buildExampleApp(),
                buildExampleGeo(),
                buildExampleDevice()
        ));
        request.setFeatureInfo(buildExampleFeatureInfo(
                Arrays.asList("宝马", "大众"),
                Collections.singletonList(
                        new DistrictFeature.ProvinceAndCity(
                                "安徽省", "合肥市")),
                Arrays.asList("台球", "游泳"),
                FeatureRelation.OR
        ));
        System.out.println(JSON.toJSONString(request));
        System.out.println(JSON.toJSONString(search.fetchAds(request)));

        // 第二个测试条件
        request.setRequestInfo(new SearchRequest.RequestInfo(
                "aaa",
                Collections.singletonList(new AdSlot(
                        "ad-y", 1,
                        1080, 720, Arrays.asList(1, 2),
                        1000
                )),
                buildExampleApp(),
                buildExampleGeo(),
                buildExampleDevice()
        ));
        request.setFeatureInfo(buildExampleFeatureInfo(
                Arrays.asList("宝马", "大众", "标志"),
                Collections.singletonList(
                        new DistrictFeature.ProvinceAndCity(
                                "安徽省", "合肥市")),
                Arrays.asList("台球", "游泳"),
                FeatureRelation.AND
        ));
        System.out.println(JSON.toJSONString(request));
        System.out.println(JSON.toJSONString(search.fetchAds(request)));

    }

    private App buildExampleApp() {
        return new App("0", "zsh",
                "qqzsh.top", "huawei");
    }

    private Geo buildExampleGeo() {
        return new Geo((float) 100.28, (float) 88.61,
                "甘肃省", "兰州市");
    }

    private Device buildExampleDevice() {

        return new Device(
                "iphone",
                "0xxxxx",
                "127.0.0.1",
                "x",
                "1080 720",
                "1080 720",
                "123456789"

        );
    }

    private SearchRequest.FeatureInfo buildExampleFeatureInfo(
            List<String> keywords,
            List<DistrictFeature.ProvinceAndCity> provinceAndCities,
            List<String> its,
            FeatureRelation relation
    ) {
        return new SearchRequest.FeatureInfo(
                new KeywordFeature(keywords),
                new DistrictFeature(provinceAndCities),
                new ItFeature(its),
                relation
        );
    }
}

