package ccpc2019;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-13 22:00
 * @Description 1560: 520
 */
public class Main1560 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger n = new BigInteger(scanner.nextLine());
        System.out.println(new BigInteger("2").modPow(n,new BigInteger("20180520")));
    }
}
