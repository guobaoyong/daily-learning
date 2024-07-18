package com.heima.common.constants;

/**
 * @author 高翔宇
 * @since 2024/2/11 周日 下午3:45
 */
public class ArticleHomeLoadConstants {
    /**
     * 加载更多
     */
    public static final Short LOAD_TYPE_IS_LOAD_MORE = 1;

    /**
     * 加载最新
     */
    public static final Short LOAD_TYPE_IS_LOAD_NEW = 2;

    /**
     * 默认频道ID，`__all__` 表示所有
     */
    public static final String DEFAULT_CHANNEL_ID = "__all__";

    /**
     * 默认加载数量
     */
    public static final Integer DEFAULT_LOAD_SIZE = 10;

    /**
     * 最大加载数量
     */
    public static final Integer MAX_LOAD_SIZE = 50;
}
