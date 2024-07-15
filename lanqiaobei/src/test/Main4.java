package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-11-06 8:50
 * @description 正则匹配时间字符串
 */
public class Main4 {

    public static void main(String[] args) {
        String s = "审核人：张三 审核时间：2019-10-11 12:00:36";
        //提取时间
        Pattern compile = Pattern.compile("(\\d{4})-(\\d{1,2})-(\\d{1,2}) (\\d{1,2}):(\\d{1,2}):(\\d{1,2})");
        Matcher matcher = compile.matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
