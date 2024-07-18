package qqzsh.top.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qqzsh.top.search.vo.feature.DistrictFeature;
import qqzsh.top.search.vo.feature.FeatureRelation;
import qqzsh.top.search.vo.feature.ItFeature;
import qqzsh.top.search.vo.feature.KeywordFeature;
import qqzsh.top.search.vo.media.AdSlot;
import qqzsh.top.search.vo.media.App;
import qqzsh.top.search.vo.media.Device;
import qqzsh.top.search.vo.media.Geo;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 17:34
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    // 媒体方的请求标识
    private String mediaId;
    // 请求基本信息
    private RequestInfo requestInfo;
    // 匹配信息
    private FeatureInfo featureInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestInfo {

        private String requestId;

        private List<AdSlot> adSlots;
        private App app;
        private Geo geo;
        private Device device;
    }

    @Data
    @NoArgsConstructor
    public static class FeatureInfo {

        private KeywordFeature keywordFeature;
        private DistrictFeature districtFeature;
        private ItFeature itFeature;

        private FeatureRelation relation = FeatureRelation.AND;

        public FeatureInfo(KeywordFeature keywordFeature,
                           DistrictFeature districtFeature,
                           ItFeature itFeature,
                           FeatureRelation relation) {
            this.keywordFeature = keywordFeature;
            this.districtFeature = districtFeature;
            this.itFeature = itFeature;
            this.relation = relation;
        }
    }
}

