package javaB_2018;

import java.math.BigInteger;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-23 8:46
 * @Describe 标题：复数幂
 *
 * 设i为虚数单位。对于任意正整数n，(2+3i)^n 的实部和虚部都是整数。
 * 求 (2+3i)^123456 等于多少？ 即(2+3i)的123456次幂，这个数字很大，要求精确表示。
 *
 * 答案写成 "实部±虚部i" 的形式，实部和虚部都是整数（不能用科学计数法表示），中间任何地方都不加空格，实部为正时前面不加正号。(2+3i)^2 写成: -5+12i，
 * (2+3i)^5 的写成: 122-597i
 */
public class Main3 {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("2");
        BigInteger b = new BigInteger("3");
        BigInteger a1 = new BigInteger("2");
        BigInteger b1 = new BigInteger("3");
        for (int i = 1; i < 123456; i++) {
            BigInteger temp = a;
            //temp = 2*a - 3*b
            a = a.multiply(a1).subtract(b.multiply(b1));
            b = temp.multiply(b1).add(b.multiply(a1));
        }
        System.out.println(a+"+"+b+"i");
    }
}
