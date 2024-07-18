package com.heima.model.search.dto;

import lombok.Data;

import java.util.Date;


/**
 * @author itheima，高翔宇
 */
@Data
public class UserSearchDto {

    /**
     * 搜索关键字
     */
    String searchWords;
    /**
     * 当前页
     */
    int pageNum;
    /**
     * 分页条数
     */
    int pageSize;
    /**
     * 最小时间
     */
    Date minBehotTime;

    /**
     * 根据当前页码和页面大小计算用于获取数据的起始索引的方法。
     *
     * @return 用于获取数据的起始索引
     */
    public int getFromIndex() {
        if (this.pageNum < 1) {
            return 0;
        }
        if (this.pageSize < 1) {
            this.pageSize = 10;
        }
        return this.pageSize * (pageNum - 1);
    }
}