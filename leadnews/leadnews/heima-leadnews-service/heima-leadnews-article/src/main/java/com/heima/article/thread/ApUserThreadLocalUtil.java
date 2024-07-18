package com.heima.article.thread;

import com.heima.model.user.entity.ApUser;

/**
 * 自媒体用户信息的ThreadLocal工具类
 *
 * @author 高翔宇
 * @since 2024/2/17 周六 下午6:56
 */
public class ApUserThreadLocalUtil {
    private static final ThreadLocal<ApUser> AP_USER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前线程的用户信息
     *
     * @param apUser 用户信息
     */
    public static void setApUser(ApUser apUser) {
        AP_USER_THREAD_LOCAL.set(apUser);
    }

    /**
     * 获取当前线程的用户信息
     *
     * @return 当前线程的用户信息
     */
    public static ApUser getApUser() {
        return AP_USER_THREAD_LOCAL.get();
    }

    /**
     * 清除当前线程的用户信息
     */
    public static void clear() {
        AP_USER_THREAD_LOCAL.remove();
    }
}
