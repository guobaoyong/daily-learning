package com.heima.gateway.admin.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器，用于校验Token
 *
 * @author 高翔宇
 * @since 2024/4/13 周六 下午1:58
 */
@Component
@Slf4j
@Order(0)
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("网关全局过滤器 AuthorizeFilter ...");
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        log.info("请求路径：{}", path);
        if (path.contains("/login")) {
            log.info("放行登录请求");
            return chain.filter(exchange);
        }
        log.info("校验Token");
        ServerHttpResponse response = exchange.getResponse();
        String token = request.getHeaders().getFirst("token");
        log.info("Token: {}", token);
        if (!StringUtils.hasLength(token)) {
            log.warn("Token为空，请求被拦截");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        } else {
            try {
                DecodedJWT decodedJwt = AppJwtUtil.decodeToken(token);
                request.mutate().header("userId", decodedJwt.getClaim("id").asString()).build();
            } catch (JWTVerificationException e) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            log.info("Token校验通过，请求继续...");
            return chain.filter(exchange);
        }
    }
}
