package bitOperation;

import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-14 14:52
 * @Describe 题3:二进制中1的个数
 * 请实现一个函数,输入一个整数,输出该数二进制表示中1的个数。
 * 例9的二进制表示为1001，有2位是1。
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //输出该数的二进制数
        System.out.println(Integer.toBinaryString(n));
        //计数
        int count = 0;
        for (int i = 0; i < 32; i++) {
            //将1左移i位后与N进行&，只有都为1时，结果才为1。
            if ((n & (1 << i)) == (1 << i)){
                count++;
            }
        }
        System.out.println(count);
        System.out.println("----------方法二------------");
        count = 0;
        for (int i = 0; i < 32; i++) {
            //n无符号右移与1&，结果为1说明当前位为1
            if (((n >>> i) & 1) == 1){
                count++;
            }
        }
        System.out.println(count);
        System.out.println("----------方法三------------");
        count=0;
        while (n != 0){
            //(x-1)&x 可以消掉最低位上的1.消掉几次，1的个数就为几。
            n = (n-1) & n;
            count++;
        }
        System.out.println(count);
    }
}
