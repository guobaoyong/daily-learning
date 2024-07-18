package com.heima.admin.interceptor;

import com.heima.admin.thread.AdminThreadLocalUtil;
import com.heima.model.admin.entity.AdUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:05
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String userId = request.getHeader("userId");
        if (userId != null) {
            AdminThreadLocalUtil.set(AdUser.builder().id(Long.parseLong(userId)).build());
        }
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        AdminThreadLocalUtil.remove();
    }
}
