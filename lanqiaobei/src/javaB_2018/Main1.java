package javaB_2018;

import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-23 8:09
 * @Describe
 * 标题：第几天
 *
 * 2000年的1月1日，是那一年的第1天。
 * 那么，2000年的5月4日，是那一年的第几天？
 *
 * 2000年为闰年，那么31+29+31+30+4=125
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        if(year%4==0&&year%100!=0||year%400==0){
            System.out.println(year+"是闰年");
        }else{
            System.out.println(year+"不是闰年");
        }
        System.out.println(31+29+31+30+4);
    }
}
