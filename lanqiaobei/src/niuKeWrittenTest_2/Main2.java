package niuKeWrittenTest_2;

import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-25 19:11
 * @Describe 【牛牛吃雪糕】
 * 最近天气太热了，牛牛每天都要吃雪糕。雪糕有一盒一份、一盒两份、一盒三份这三种包装，牛牛一天可以吃多盒雪糕，但是只能吃六份，吃多了就
 * 会肚子疼，吃少了就会中暑。而且贪吃的牛牛一旦打开一盒雪糕，就一定会把它吃完。请问牛牛能健康地度过这段高温期么？
 * 输入描述：
 * 每个输入包含多个测试用例。
 * 输入的第一行包括一个正整数，表示数据组数T(1<=T<=100)。
 * 接下来N行，每行包含四个正整数，表示高温期持续的天数N(1<=N<=10000)，一盒一份包装的雪糕数量A(1<=A<=100000)，一盒两份包装的雪糕数量B(1<=B<=1
 * 00000)，一盒三份包装的雪糕数量C(1<=A<=100000)。
 * 输出描述：
 * 对于每个用例，在单独的一行中输出结果。如果牛牛可以健康地度过高温期则输出"Yes"，否则输出"No"。
 *
 * 示例1：
 * 输入
 * 4
 * 1 1 1 1
 * 2 0 0 4
 * 3 0 2 5
 * 4 24 0 0
 * 输出
 * Yes
 * Yes
 * No
 * Yes
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- != 0) {
            int s = scanner.nextInt();
            int one = scanner.nextInt();
            int two = scanner.nextInt();
            int three = scanner.nextInt();
            boolean res = getResult(s, one, two, three);
            System.out.println(res ? "Yes" : "No");
        }
    }

    private static boolean getResult(int s, int one, int two, int three) {
        int sum = one + two * 2 + three * 3;
        s *= 6;
        if (sum < s) return false;
        if (sum - s == 1) {
            return one >= 1;
        }
        return true;
    }
}
