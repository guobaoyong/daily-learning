package javaB_2018;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-23 10:00
 * @Describe
标题：螺旋折线

如图p1.pgn所示的螺旋折线经过平面上所有整点恰好一次。
对于整点(X, Y)，我们定义它到原点的距离dis(X, Y)是从原点到(X, Y)的螺旋折线段的长度。

例如dis(0, 1)=3, dis(-2, -1)=9

给出整点坐标(X, Y)，你能计算出dis(X, Y)吗？

【输入格式】
X和Y

对于40%的数据，-1000 <= X, Y <= 1000
对于70%的数据，-100000 <= X， Y <= 100000
对于100%的数据, -1000000000 <= X, Y <= 1000000000

【输出格式】
输出dis(X, Y)


【输入样例】
0 1

【输出样例】
3


资源约定：
峰值内存消耗（含虚拟机） < 256M
CPU消耗  < 1000ms


请严格按要求输出，不要画蛇添足地打印类似：“请您输入...” 的多余内容。

所有代码放在同一个源文件中，调试通过后，拷贝提交该源码。
不要使用package语句。不要使用jdk1.7及以上版本的特性。
主类的名字必须是：Main，否则按无效代码处理。
 */
public class Main7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println(dis(x, y));
    }

    private static BigInteger dis(int x, int y) {// 使用BigInteger-->避免大数相乘产生溢出情况
        int n = max(x, y);// 找到最外圈是第几圈
        int k = n - 1;// k表示内圈总数
        int lost = g(x, y, n);// 计算外圈路径长度
        BigInteger most1 = new BigInteger(Integer.toString(8 + k * 8));
        BigInteger most2 = new BigInteger(Integer.toString(k / 2));
        /**
         * most = most1*most2
         * most = (8+k*8)*k/2 --->内圈总数是一个数列求和：1 8 16 24
         */
        BigInteger l = new BigInteger(Integer.toString(lost));
        return l.add((most1.multiply(most2)));
    }

    private static int g(int x, int y, int n) {
        /**
         * 分成两大部分计算：
         * 1.表示顶点处
         * 2.表示每个边的情况
         */
        if (Math.abs(x) == Math.abs(y)) {
            if (x < 0 && y > 0)
                return 2 * n;
            else if (x > 0 && y > 0)
                return 4 * n;
            else if (x > 0 && y < 0)
                return 6 * n;
            else
                return x == 0 ? 0 : 8 * n;
        } else {
            if (x == -n)
                return n + y;
            else if (x == n)
                return 4 * n + n - y;
            else if (y == n)
                return 2 * n + n + x;
            else
                return 6 * 2 + n - x;
        }
    }

    private static int max(int x, int y) {
        //求最外边的圈数
        if (Math.abs(x) >= Math.abs(y))
            return Math.abs(x);
        else
            return Math.abs(y);
    }
}
