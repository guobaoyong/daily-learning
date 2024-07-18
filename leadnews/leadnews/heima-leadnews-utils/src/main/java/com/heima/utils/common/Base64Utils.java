package com.heima.utils.common;


import java.util.Arrays;
import java.util.Base64;

/**
 * @author 高翔宇
 * @since 2024-02-6, 周二, 18:41
 */
public class Base64Utils {

    /**
     * 解码
     *
     * @param base64
     * @return
     */
    public static byte[] decode(String base64) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(base64);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += (byte) 256;
                }
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 编码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(byte[] data) {
        Base64.Encoder encoder = Base64.getEncoder();
        return Arrays.toString(encoder.encode(data));
    }
}