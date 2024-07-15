package 递归与迭代;

import org.junit.jupiter.api.Test;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-27 16:16
 * @Describe 迭代实现
 * 有n阶台阶，1次只能上一步或者2步，有多少种走法
 * 递归
 * 迭代
 */
public class TestStep2 {

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        System.out.println(loop(40)); //165580141
        long end = System.currentTimeMillis();
        System.out.println(end - start); //<1ms
    }

    public int loop(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int one = 2; //走到第二级台阶的初始化值
        int two = 1; //走到第一级台阶的初始化值
        int sum = 0;

        //最后跨两步+最后跨一步的走法
        for (int i = 3; i <= n; i++) {
            sum = two + one;
            two = one;
            one = sum;
        }
        return sum;
    }
}
