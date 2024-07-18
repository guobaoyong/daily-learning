package qqzsh.top.dianping.controller;

import com.alibaba.druid.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.common.CommonRes;
import qqzsh.top.dianping.common.EmBusinessError;
import qqzsh.top.dianping.pojo.Category;
import qqzsh.top.dianping.pojo.Shop;
import qqzsh.top.dianping.service.CategoryService;
import qqzsh.top.dianping.service.ShopService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @site https://www.qqzop
 * @create 2019-12-18 15:13
 * @description 前台门店相关
 */
@RestController("/shop")
@RequestMapping("/shop")
@AllArgsConstructor
public class ShopController {

    private ShopService shopService;
    private CategoryService categoryService;

    //推荐服务V1.0
    @RequestMapping("/recommend")
    public CommonRes recommend(@RequestParam(name="longitude")BigDecimal longitude,
                               @RequestParam(name="latitude")BigDecimal latitude) throws BusinessException {
        if(longitude == null || latitude == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<Shop> shopList = shopService.recommend(longitude,latitude);
        return CommonRes.create(shopList);
    }


    //搜索服务V1.0
    @RequestMapping("/search")
    public CommonRes search(@RequestParam(name="longitude") BigDecimal longitude,
                            @RequestParam(name="latitude")BigDecimal latitude,
                            @RequestParam(name="keyword")String keyword,
                            @RequestParam(name="orderby",required = false)Integer orderby,
                            @RequestParam(name="categoryId",required = false)Integer categoryId,
                            @RequestParam(name="tags",required = false)String tags) throws BusinessException {
        if(StringUtils.isEmpty(keyword) || longitude == null || latitude == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<Shop> shopList = shopService.search(longitude,latitude,keyword,orderby,categoryId,tags);
        List<Category> categoryList = categoryService.selectAll();
        List<Map<String,Object>> tagsAggregation = shopService.searchGroupByTags(keyword,categoryId,tags);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("shop",shopList);
        resMap.put("category",categoryList);
        resMap.put("tags",tagsAggregation);
        return CommonRes.create(resMap);

    }




}

