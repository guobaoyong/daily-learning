package recursion;

import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-18 9:13
 * @Describe 题1·小白上楼梯(递归设计)
 * 小白正在上楼梯，楼梯有n阶台阶，
 * 小白一次可以上1阶、2阶或者3阶，
 * 实现一个方法，计算小白有多少种走完楼梯的方式
 */
public class Main4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int res = f(n);
        System.out.println(res);
    }

    /**
     * 递归求上楼梯的次数
     * @param n n阶台阶
     * @return 上楼梯的次数
     * 找重复：
     * 找变化的量：n
     * 找出口： n==0 , n == 1 , n== 2
     */
    static int f(int n) {
        if (n == 0){
            return 1;
        }else if (n == 1){
            return 1;
        }else if (n == 2){
            return 2;
        }
        return f(n-1)+f(n-2)+f(n-3);
    }

}
