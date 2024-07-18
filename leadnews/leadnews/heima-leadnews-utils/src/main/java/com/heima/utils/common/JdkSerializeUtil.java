package com.heima.utils.common;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * jdk序列化
 *
 * @author itheima
 */
@Slf4j
public class JdkSerializeUtil {

    /**
     * 序列化
     */
    public static <T> byte[] serialize(T obj) {

        if (obj == null) {
            throw new NullPointerException();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (Exception ex) {
            log.error("序列化失败", ex);
        }
        return new byte[0];
    }

    /**
     * 反序列化
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (Exception ex) {
            log.error("反序列化失败", ex);
        }
        return null;
    }
}
