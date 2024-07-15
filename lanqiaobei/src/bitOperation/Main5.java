package bitOperation;

import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-14 16:18
 * @Describe 题5：将整数的奇偶位交换
 */
public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //奇数位
        int c = n & 0x55555555; //0101 0101 ...
        //偶数位
        int d = n & 0xaaaaaaaa; //1010 1010 ...
        //奇数位左移1，偶数位右移1，然后异或
        System.out.println((c<<1)^(d>>1));
    }
}
