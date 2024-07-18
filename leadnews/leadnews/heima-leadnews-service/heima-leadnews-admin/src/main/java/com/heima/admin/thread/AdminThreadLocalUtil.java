package com.heima.admin.thread;

import com.heima.model.admin.entity.AdUser;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:04
 */
public class AdminThreadLocalUtil {
    private final static ThreadLocal<AdUser> AD_USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(AdUser adUser) {
        AD_USER_THREAD_LOCAL.set(adUser);
    }

    public static AdUser get() {
        return AD_USER_THREAD_LOCAL.get();
    }

    public static void remove() {
        AD_USER_THREAD_LOCAL.remove();
    }
}
