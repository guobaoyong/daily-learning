package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.entity.ApArticle;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/2/11 周日 下午2:52
 */
public interface ApArticleMapper extends BaseMapper<ApArticle> {
    /**
     * 加载文章列表
     *
     * @param maxBehotTime 最大时间
     * @param minBehotTime 最小时间
     * @param size         分页大小
     * @param tag          频道ID
     * @param type         类型，1加载更多，2加载最新
     * @return 文章列表
     */
    List<ApArticle> loadArticleList(@Param("maxBehotTime") Date maxBehotTime,
                                    @Param("minBehotTime") Date minBehotTime,
                                    @Param("size") Integer size,
                                    @Param("tag") String tag,
                                    @Param("type") Short type);
}
