package com.heima.utils.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * JWT是token的一种，一个JWT字符串包含三个部分
 *
 * @author 高翔宇
 * @since 2024-02-8, 周四, 18:55
 */
public class AppJwtUtil {
    // 签名密钥
    private static final String SECRET = "heima-leadnews";

    public static String generateToken(String id) {
        return generateToken(Map.of("id", id));
    }

    /**
     * 生成token
     *
     * @param payload token携带的信息
     * @return token字符串
     */
    private static String generateToken(Map<String, String> payload) {
        JWTCreator.Builder jwtBuilder = initJwtBuilder();
        // 构建payload
        payload.forEach(jwtBuilder::withClaim);
        // 签名算法
        return sign(jwtBuilder);
    }

    private static JWTCreator.Builder initJwtBuilder() {
        JWTCreator.Builder jwtBuilder = JWT.create();
        // 指定token过期时间
        Calendar calendar = Calendar.getInstance();
        // 有效时间
        calendar.add(Calendar.HOUR, 3);
        // 指定签发时间、过期时间 和 签名算法等，并返回token
        return jwtBuilder
                // 签发者
                .withIssuer("heima")
                // 签发时间
                .withIssuedAt(new Date())
                // 过期时间
                .withExpiresAt(calendar.getTime())
                // 说明
                .withSubject("system")
                // 接受者
                .withAudience("app");
    }

    private static String sign(JWTCreator.Builder jwtBuilder) {
        return jwtBuilder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 解析token
     *
     * @param token token字符串
     * @return 解析后的token类
     */
    public static DecodedJWT decodeToken(String token) {
        JWTVerifier jwtVerifier = JWT
                .require(Algorithm.HMAC256(SECRET))
                .build();
        DecodedJWT decodedJwt;
        try {
            decodedJwt = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("token解析失败", e);
        }
        return decodedJwt;
    }

    /**
     * 获取请求载荷
     *
     * @param token token字符串
     * @return 请求载荷
     */
    public static Map<String, Claim> getClaim(String token) {
        return decodeToken(token).getClaims();
    }
}
