package bksf_2019_xz;

import java.util.Scanner;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-10 18:56
 * @description
 * 给出n个正整数，要求找出相邻两个数字中差的绝对值最小的一对数字，如果有差的绝对值相同的，则输出最前面的一对数。
 *
 * 2<n<=100，正整数都在10^16范围内
 *
 * 输入
 * 输入包含2行，第一行为n，第二行是n个用空格分隔的正整数。
 *
 * 输出
 * 输出包含一行两个正整数，要求按照原来的顺序输出
 *
 *
 * 样例输入
 * 9
 * 1 3 4 7 2 6 5 12 32
 * 样例输出
 * 3 4
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] s = new long[n];
        for (int i = 0; i < n; i++) {
            s[i] = scanner.nextLong();
        }

        long min = s[1]-s[0];
        long x = 0;
        long y = 0;
        for (int i = 0; i < s.length; i++) {
            if (i != s.length-1){
                if (min > Math.abs(s[i+1]-s[i])){
                    min = Math.abs(s[i+1]-s[i]);
                    x = s[i];
                    y = s[i+1];
                }
            }
        }
        System.out.println(x+" "+y);
    }
}
