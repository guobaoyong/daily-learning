package com.heima.wemedia.thread;

import com.heima.model.wemedia.entity.WmUser;

/**
 * 自媒体用户信息的ThreadLocal工具类
 *
 * @author 高翔宇
 * @since 2024/2/17 周六 下午6:56
 */
public class WmUserThreadLocalUtil {
    private static final ThreadLocal<WmUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前线程的用户信息
     *
     * @param wmUser 用户信息
     */
    public static void setWmUser(WmUser wmUser) {
        WM_USER_THREAD_LOCAL.set(wmUser);
    }

    /**
     * 获取当前线程的用户信息
     *
     * @return 当前线程的用户信息
     */
    public static WmUser getWmUser() {
        return WM_USER_THREAD_LOCAL.get();
    }

    /**
     * 清除当前线程的用户信息
     */
    public static void clear() {
        WM_USER_THREAD_LOCAL.remove();
    }
}
