package niuKeWrittenTest_5;

import java.util.Scanner;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-15 19:31
 * @description
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] xx = new int[n][n];
        for (int j = 0; j < n; j++) {
            int x = scanner.nextInt()-1;
            int y = scanner.nextInt()-1;
            xx[x][y] = 1;
        }

        int m = scanner.nextInt();

        for (int i = 0; i < m; i++) {
            int a1 = scanner.nextInt()-1;
            int b1 = scanner.nextInt()-1;
            int a2 = scanner.nextInt()-1;
            int b2 = scanner.nextInt()-1;
            int sum = 0;
            for (int j = a1; j <=a2 ; j++) {
                for (int k = b1; k <= b2; k++) {
                    if (xx[j][k] == 1){
                        sum++;
                    }
                }
            }
            System.out.println(sum);
        }
    }
}
