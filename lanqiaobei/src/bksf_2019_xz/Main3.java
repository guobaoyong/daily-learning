package bksf_2019_xz;

import java.util.Scanner;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-10 18:56
 * @description
 * 举重大赛开始了，为了保证公平，要求比赛的双方体重较小值要大于等于较大值的90%，那么对于这N个人最多能进行多少场比赛呢，任意两人之间最多进行一场比赛。
 *
 * 输入
 * 第一行N，表示参赛人数（2<=N<=10^5）
 *
 * 第二行N个正整数表示体重（0<体重<=10^8）
 *
 * 输出
 * 一个数，表示最多能进行的比赛场数
 *
 *
 * 样例输入
 * 5
 * 1 1 1 1 1
 * 样例输出
 * 10
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = scanner.nextInt();
        }
        int num = 0;
        for (int i = 0; i < s.length; i++) {
            if (i != s.length -1){
                for (int j = i+1; j < s.length; j++) {
                    if (s[i] >= s[j] && s[i]*0.9 <= s[j]){
                        num++;
                    }else {
                        if (s[i] <= s[j] && s[j]*0.9 <= s[i]){
                            num++;
                        }
                    }
                }
            }
        }
        System.out.println(num);
    }
}
