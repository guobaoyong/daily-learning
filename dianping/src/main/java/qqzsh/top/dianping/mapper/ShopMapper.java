package qqzsh.top.dianping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qqzsh.top.dianping.pojo.Shop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-15 10:46
 * @Description 门店数据层接口
 */
public interface ShopMapper extends BaseMapper<Shop> {

    /**
     * 推荐服务V1.0
     * @param longitude
     * @param latitude
     * @return
     */
    List<Shop> recommend(@Param("longitude") BigDecimal longitude, @Param("latitude") BigDecimal latitude);

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
    List<Shop> search(@Param("longitude") BigDecimal longitude,
                           @Param("latitude") BigDecimal latitude,
                           @Param("keyword")String keyword,
                           @Param("orderby")Integer orderby,
                           @Param("categoryId")Integer categoryId,
                           @Param("tags")String tags);

    /**
     * 通过标签搜索
     * 搜索服务V1.0
     * @param keyword
     * @param categoryId
     * @param tags
     * @return
     */
    List<Map<String,Object>> searchGroupByTags(@Param("keyword")String keyword,
                                               @Param("categoryId")Integer categoryId,
                                               @Param("tags")String tags);

}
