package org.wlgzs.util;

import io.netty.buffer.ByteBuf;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-04 10:54
 * @Describe
 */
public class convertByteBufToString {

    public static String convertByteBufToString(ByteBuf buf) {
        String str;
        if(buf.hasArray()) { // 处理堆缓冲区
            str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else { // 处理直接缓冲区以及复合缓冲区
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            str = new String(bytes, 0, buf.readableBytes());
        }
        return str;
    }
}
