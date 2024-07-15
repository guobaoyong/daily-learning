package org.wlgzs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-02 22:11
 * @Describe 键盘数据的输入工具类
 */
public class InputUtil {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));
    private InputUtil(){}

    /**
     * 实现键盘数据的输入操作 ，可以返回的数据类型为String
     * @param prompt 提示信息
     * @return 输入的数据返回
     */
    public static String getString(String prompt){
        // 数据接收标记
        boolean flag = true;
        String str = null;
        while (flag){
            System.out.println(prompt);
            try {
                // 读取一行数据
                str = KEYBOARD_INPUT.readLine();
                if (str == null || "".equals(str)){
                    System.out.println("数据输入错误 ，该内容不允许为空：");
                }else {
                    flag = false;
                }
            } catch (IOException e) {
                System.out.println("数据输入错误 ，该内容不允许为空：");
            }
        }
        return str;
    }


}
