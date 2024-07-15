package acm_2018.zq;

import javafx.scene.transform.Scale;

import java.util.Scanner;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-04 10:16
 * @Description
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String s1 = "";
            System.out.println(s.length());
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) >= 'A' && s.charAt(j) <= 'Z'){
                    s1 += s.charAt(j);
                }
            }
            if (s1.length() <= 5 && !s1.equals("")){
                System.out.println(s1);
            }
        }
    }
}
