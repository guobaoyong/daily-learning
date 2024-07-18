package com.heima.search.interceptor;

import com.heima.search.thread.ApUserThreadLocalUtil;
import com.heima.model.user.entity.ApUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 高翔宇
 * @since 2024-04-12, 周五, 16:17
 */
public class ApArticleInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String userId = request.getHeader("userId");
        if (StringUtils.hasLength(userId)) {
            // 将当前线程的用户信息设置为请求头中的用户信息
            ApUserThreadLocalUtil.setApUser(ApUser.builder().id(Long.valueOf(userId)).build());
        }
        return true;
    }

    /**
     * 在请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        // 清除当前线程的用户信息。我们知道，Tomcat线程池的线程是复用的，如果不清除，可能会导致线程复用时，线程中的用户信息还存在，导致数据混乱。
        ApUserThreadLocalUtil.clear();
    }
}
