package bitOperation;

import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-14 15:54
 * @Describe 题4:是不是2的整数次方
 * 用一条语句判断一个整数是不是2的整数次方
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (((n-1) &n) == 0){
            System.out.println(true);
        }else {
            System.out.println(false);
        }

    }
}
