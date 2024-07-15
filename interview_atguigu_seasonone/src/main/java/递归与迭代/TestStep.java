package 递归与迭代;

import org.junit.jupiter.api.Test;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-27 15:58
 * @Describe 递归实现
 * 有n阶台阶，1次只能上一步或者2步，有多少种走法
 * 递归
 * 迭代
 */
public class TestStep {
    @Test
    public void test() {
        long start = System.currentTimeMillis();
        System.out.println(f(40)); //165580141
        long end = System.currentTimeMillis();
        System.out.println(end - start); //640ms
    }

    //递归实现f(n)：求n步台阶，一共有几种走法
    public int f(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return f(n - 1) + f(n - 2);
    }

}
