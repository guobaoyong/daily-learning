package pywrittenTest;

import java.util.Calendar;
import java.util.Scanner;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-29 19:16
 * @Description
 */
public class Main2 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Integer i = scanner.nextInt();
            Integer j = scanner.nextInt();
            //小时
            Integer h1 = i / 100;
            Integer h2 = j / 100;
            //分钟
            Integer m1 = i % 100;
            Integer m2 = j % 100;
            if (m2 - m1 < 0){
                Integer h = h2-h1-1;
                Integer m = m2-m1+60;
                System.out.println("航班飞行时间为"+h+"小时"+m+"分钟");
            }else {
                Integer h = h2-h1;
                Integer m = m2-m1;
                System.out.println("航班飞行时间为"+h+"小时"+m+"分钟");
            }
        }catch (Exception e){
            System.out.println("您的输入有误，请检查！");
        }
    }
}
