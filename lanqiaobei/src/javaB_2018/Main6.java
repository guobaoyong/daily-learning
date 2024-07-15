package javaB_2018;

import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-23 9:54
 * @Describe
标题：递增三元组

给定三个整数数组
A = [A1, A2, ... AN],
B = [B1, B2, ... BN],
C = [C1, C2, ... CN]，
请你统计有多少个三元组(i, j, k) 满足：

1. 1 <= i, j, k <= N
2. Ai < Bj < Ck

【输入格式】
第一行包含一个整数N。
第二行包含N个整数A1, A2, ... AN。
第三行包含N个整数B1, B2, ... BN。
第四行包含N个整数C1, C2, ... CN。

对于30%的数据，1 <= N <= 100
对于60%的数据，1 <= N <= 1000
对于100%的数据，1 <= N <= 100000 0 <= Ai, Bi, Ci <= 100000

【输出格式】
一个整数表示答案

【输入样例】
3
1 1 1
2 2 2
3 3 3

【输出样例】
27


资源约定：
峰值内存消耗（含虚拟机） < 256M
CPU消耗  < 1000ms


请严格按要求输出，不要画蛇添足地打印类似：“请您输入...” 的多余内容。
所有代码放在同一个源文件中，调试通过后，拷贝提交该源码。
不要使用package语句。不要使用jdk1.7及以上版本的特性。
主类的名字必须是：Main，否则按无效代码处理。

 */
public class Main6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int N = scanner.nextInt();
        int[][] d = new int[N][3];
        for (int i = 0; i < N; i++) {
            d[i][0] = scanner.nextInt();
            d[i][1] = scanner.nextInt();
            d[i][2] = scanner.nextInt();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (d[0][i] < d[1][j] && d[1][j] < d[2][k]){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
