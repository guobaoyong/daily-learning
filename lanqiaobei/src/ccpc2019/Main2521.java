package ccpc2019;

import java.util.Scanner;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-13 21:25
 * @Description 2521：文本修正
 */
public class Main2521 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] s1 = s.split(" ");
        for (int i = 0; i < s1.length; i++) {
            if (s1[i].equals("henan")){
                s1[i] = "Henan";
            }
            System.out.print(s1[i]+" ");
        }
    }
}
