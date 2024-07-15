package bksf_2019_xz;

import java.util.Scanner;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-10 18:56
 * @description
 * 小希偶然得到了传说中的月光宝盒, 然而打开月光宝盒需要一串密码。虽然小希并不知道密码具体是什么，但是月光宝盒的说明书上有着一个长度为 n (2 <= N <= 50000)的序列 a (-10^9 <= a[i] <= 10^9)的范围内。下面写着一段话：密码是这个序列的最长的严格上升子序列的长度(严格上升子序列是指，子序列的元素是严格递增的，例如: [5,1,6,2,4]的最长严格上升子序列为[1,2,4])，请你帮小希找到这个密码。
 *
 *
 * 输入
 * 第1行：1个数N，N为序列的长度(2<=N<=50000)
 *
 * 第2到 N+1行：每行1个数，对应序列的元素(-10^9 <= a[i] <= 10^9)
 *
 * 输出
 * 一个正整数表示严格最长上升子序列的长度
 *
 *
 * 样例输入
 * 8
 * 5
 * 1
 * 6
 * 8
 * 2
 * 4
 * 5
 * 10
 * 样例输出
 * 5
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = scanner.nextInt();
        }
        System.out.println(s.length);
        int x = 0;
        int num = 0;
        for (int i = 0; i < s.length; i++) {
            int a = s[i];
            if (i != s.length-1){
                for (int j = i+1; j < s.length; j++) {
                    int b = s[j];
                    if (b>a){
                        num++;
                        a = b;
                    }
                }
                if (num > x){
                    x = num;
                }
                num = 0;
            }
        }
        System.out.println(x);
    }
}
