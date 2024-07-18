package qqzsh.top.sponsor.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;
import qqzsh.top.common.exception.AdException;

import java.util.Date;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 11:18
 * @Description 通用工具类
 */
public class CommonUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    //md5加密
    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    //时间格式转换
    public static Date parseStringDate(String dateString)
            throws AdException {
        try {
            return DateUtils.parseDate(
                    dateString, parsePatterns
            );
        } catch (Exception ex) {
            throw new AdException(ex.getMessage());
        }
    }
}

