package javaB_2018;

import java.util.Scanner;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-23 10:36
 * @Describe 标题：全球变暖
 * <p>
 * 你有一张某海域NxN像素的照片，"."表示海洋、"#"表示陆地，如下所示：
 * <p>
 * .......
 * .##....
 * .##....
 * ....##.
 * ..####.
 * ...###.
 * .......
 * <p>
 * 其中"上下左右"四个方向上连在一起的一片陆地组成一座岛屿。例如上图就有2座岛屿。
 * <p>
 * 由于全球变暖导致了海面上升，科学家预测未来几十年，岛屿边缘一个像素的范围会被海水淹没。具体来说如果一块陆地像素与海洋相邻(上下左右四个相邻像素中有海洋)，它就会被淹没。
 * <p>
 * 例如上图中的海域未来会变成如下样子：
 * <p>
 * .......
 * .......
 * .......
 * .......
 * ....#..
 * .......
 * .......
 * <p>
 * 请你计算：依照科学家的预测，照片中有多少岛屿会被完全淹没。
 * <p>
 * 【输入格式】
 * 第一行包含一个整数N。  (1 <= N <= 1000)
 * 以下N行N列代表一张海域照片。
 * <p>
 * 照片保证第1行、第1列、第N行、第N列的像素都是海洋。
 * <p>
 * 【输出格式】
 * 一个整数表示答案。
 * <p>
 * 【输入样例】
 * 7
 * .......
 * .##....
 * .##....
 * ....##.
 * ..####.
 * ...###.
 * .......
 * <p>
 * 【输出样例】
 * 1
 * <p>
 * <p>
 * <p>
 * 资源约定：
 * 峰值内存消耗（含虚拟机） < 256M
 * CPU消耗  < 1000ms
 * <p>
 * <p>
 * 请严格按要求输出，不要画蛇添足地打印类似：“请您输入...” 的多余内容。
 * <p>
 * 所有代码放在同一个源文件中，调试通过后，拷贝提交该源码。
 * 不要使用package语句。不要使用jdk1.7及以上版本的特性。
 * 主类的名字必须是：Main，否则按无效代码处理。
 */
public class Main9 {
    static int n, map[][], vis[][];

    static boolean dfs(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n || vis[i][j] == 1) return false;
        vis[i][j] = 1;
        boolean f = false;
        if (map[i][j + 1] == 1 && map[i][j - 1] == 1 && map[i - 1][j] == 1 && map[i + 1][j] == 1)
            f = true;
        return dfs(i, j + 1) || dfs(i, j - 1) || dfs(i + 1, j) || dfs(i - 1, j) || f;
    }

    public static void main(String args[]) {
        int r = 0, t = 0;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        vis = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < n; j++)
                if (s.charAt(j) != '.')
                    map[i][j] = 1;
                else
                    vis[i][j] = 1;
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (vis[i][j] != 1) {

                    r++;
                    t += dfs(i, j) ? 1 : 0;

                }
        System.out.println(r - t);
    }
}
