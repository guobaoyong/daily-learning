package ccpc2019;

import dataStructure.day01.ArrayStack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-13 21:30
 * @Description 2522: 咕咕的的复复读读机机
 */
public class Main2522 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a[] = new int[n];
        int b[] = new int[101];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for(int i = 0; i < n; i++){
           int x = a[i];
           b[x]++;
        }

        int max = 0,sum = 0;
        for (int i = 1; i < b.length; i++) {
            if (b[i] > max){
                max = b[i];
                sum = i;
            }
        }
        System.out.println(sum);
    }
}
