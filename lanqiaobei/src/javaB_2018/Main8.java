package javaB_2018;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-23 10:22
 * @Describe
标题：日志统计

小明维护着一个程序员论坛。现在他收集了一份"点赞"日志，日志共有N行。其中每一行的格式是：

ts id

表示在ts时刻编号id的帖子收到一个"赞"。

现在小明想统计有哪些帖子曾经是"热帖"。如果一个帖子曾在任意一个长度为D的时间段内收到不少于K个赞，小明就认为这个帖子曾是"热帖"。

具体来说，如果存在某个时刻T满足该帖在[T, T+D)这段时间内(注意是左闭右开区间)收到不少于K个赞，该帖就曾是"热帖"。

给定日志，请你帮助小明统计出所有曾是"热帖"的帖子编号。

【输入格式】
第一行包含三个整数N、D和K。
以下N行每行一条日志，包含两个整数ts和id。

对于50%的数据，1 <= K <= N <= 1000
对于100%的数据，1 <= K <= N <= 100000 0 <= ts <= 100000 0 <= id <= 100000

【输出格式】
按从小到大的顺序输出热帖id。每个id一行。

【输入样例】
7 10 2
0 1
0 10
10 10
10 1
9 1
100 3
100 3

【输出样例】
1
3

资源约定：
峰值内存消耗（含虚拟机） < 256M
CPU消耗  < 1000ms


请严格按要求输出，不要画蛇添足地打印类似：“请您输入...” 的多余内容。

所有代码放在同一个源文件中，调试通过后，拷贝提交该源码。
不要使用package语句。不要使用jdk1.7及以上版本的特性。
主类的名字必须是：Main，否则按无效代码处理。

 */
public class Main8 {
    private static int N = 0; //代表有多少行
    private static int D = 0; //代表这个时间段
    private static int K = 0; //代表赞数的多少

    private static Map<Integer, Integer> map = new HashMap<Integer,Integer>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        D = scanner.nextInt();
        K = scanner.nextInt();

        int[][] data = new int[N][2];
        for(int i=0;i<N;i++) {
            data[i][0] = scanner.nextInt();
            data[i][1] = scanner.nextInt();
        }
        int[] ts = new int[N];
        int[] id = new int[N];
        for(int i=0;i<N;i++) {
            ts[i] = data[i][0];
            id[i] = data[i][1];
        }
        jisuan(ts, id);

        for(int i=1;i<=map.size();i++) {
            System.out.print(map.get(i));
            System.out.println();
        }
    }

    public static void jisuan(int[] ts,int[] id) {

        int k = 1;

        for(int i=0;i<N-1;i++) {
            for(int j=0;j<N-i-1;j++) {
                if(ts[j]>ts[j+1]) {
                    int temp = ts[j];
                    ts[j] = ts[j+1];
                    ts[j+1] = temp;

                    temp = id[j];
                    id[j] = id[j+1];
                    id[j+1] = temp;
                }
            }
        }

        for(int i=0;i<N;i++) {
            int temp = ts[i] + D;
            int count = 0;
            for(int j = i;j<N;j++) {
                if(ts[j]>temp) {
                    break;
                }
                if(ts[j]<temp&&id[j]==id[i]) {
                    count++;
                }
            }
            if(count>=K) {
                if(!map.containsValue(id[i])) {
                    map.put(k,id[i] );
                    k++;
                }
            }
        }
    }
}
