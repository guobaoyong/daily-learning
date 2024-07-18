package qqzsh.top.dianping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.pojo.Shop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-15 10:47
 * @Description 门店接口
 */
public interface ShopService extends IService<Shop> {

    /**
     * 保存门店
     * @param shop
     * @return
     * @throws BusinessException
     */
    Shop create(Shop shop) throws BusinessException;

    /**
     * 根据ID获取门店
     * @param id
     * @return
     */
    Shop get(Long id);

    /**
     * 获取所有门店
     * @return
     */
    List<Shop> selectAll();

    /**
     * 推荐服务V1.0
     * @param longitude
     * @param latitude
     * @return
     */
    List<Shop> recommend(BigDecimal longitude, BigDecimal latitude);

    /**
     * 通过标签搜索
     * 搜索服务V1.0
     * @param keyword
     * @param categoryId
     * @param tags
     * @return
     */
    List<Map<String,Object>> searchGroupByTags(String keyword, Integer categoryId, String tags);

    /**
     * 搜索服务V1.0
     * @param longitude
     * @param latitude
     * @param keyword
     * @param orderby
     * @param categoryId
     * @param tags
     * @return
     */
    List<Shop> search(BigDecimal longitude,BigDecimal latitude,
                           String keyword,Integer orderby,Integer categoryId,String tags);
}
