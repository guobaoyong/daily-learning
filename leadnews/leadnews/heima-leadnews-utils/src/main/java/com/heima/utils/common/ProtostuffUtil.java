package com.heima.utils.common;


import com.heima.model.wemedia.entity.WmNews;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * @author itheima
 */
@Slf4j
public class ProtostuffUtil {

    /**
     * 序列化
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T t) {
        Schema schema = RuntimeSchema.getSchema(t.getClass());
        return ProtostuffIOUtil.toByteArray(t, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

    }

    /**
     * 反序列化
     */
    public static <T> T deserialize(byte[] bytes, Class<T> c) {
        T t = null;
        try {
            t = c.getDeclaredConstructor().newInstance();
            Schema<T> schema = RuntimeSchema.getSchema(c);
            ProtostuffIOUtil.mergeFrom(bytes, t, schema);
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("反序列化失败", e);
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    /**
     * jdk序列化与protostuff序列化对比
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            WmNews wmNews = new WmNews();
            JdkSerializeUtil.serialize(wmNews);
        }
        System.out.println(" jdk 花费 " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            WmNews wmNews = new WmNews();
            ProtostuffUtil.serialize(wmNews);
        }
        System.out.println(" protostuff 花费 " + (System.currentTimeMillis() - start));
    }
}
