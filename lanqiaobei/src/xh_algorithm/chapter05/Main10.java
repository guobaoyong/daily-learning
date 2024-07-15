package xh_algorithm.chapter05;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-11-07 14:49
 * @description 金矿问题，背包问题
 * 动态规划
 */
public class Main10 {

    /**
     * 获得金矿最优收益
     * 递归O(2^n)
     * @param w 工人数
     * @param n 可选金矿数
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     * @return
     */
    public static int getBestGoldMining(int w, int n, int[] p, int[] g) {
        if (w == 0 || n == 0) {
            return 0;
        }
        if (w < p[n - 1]) {
            return getBestGoldMining(w, n - 1, p, g);
        }
        return Math.max(getBestGoldMining(w, n - 1, p, g),
                getBestGoldMining(w - p[n - 1], n - 1, p, g) + g[n - 1]);
    }

    /**
     * 获得金矿最优收益
     * 自底向上求解
     * 时间复杂度和空间复杂度都为O(nw)
     * @param w 工人数
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     * @return
     */
    public static int getBestGoldMiningV2(int w, int[] p, int[] g) {
        //创建表格
        int[][] resultTable = new int[g.length + 1][w + 1];
        //填充表格
        for (int i = 1; i <= g.length; i++) {
            for (int j = 0; j <= w; j++) {
                if (j < p[i - 1]) {
                    resultTable[i][j] = resultTable[i - 1][j];
                } else {
                    resultTable[i][j] = Math.max(resultTable[i - 1][j],
                            resultTable[i - 1][j - p[i - 1]] + g[i - 1]);
                }
            }
        }
        //返回最后一个格子的值
        return resultTable[g.length][w];
    }

    /**
     * 获得金矿最优收益
     * 自底向上求解
     * 时间复杂度O(nw)、空间复杂度都为O(n)
     * @param w 工人数
     * @param p 金矿开采所需的工人数量
     * @param g 金矿储量
     * @return
     */
    public static int getBestGoldMiningV3(int w, int[] p, int[] g) {
        //创建当前结果
        int[] result = new int[w + 1];
        //填充一维数组
        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j >= 1; j--) {
                if (j >= p[i - 1]) {
                    result[j] = Math.max(result[j],
                            result[j - p[i - 1]] + g[i - 1]);
                }
            }
        }
        //返回最后一个格子的值
        return result[w];
    }


    public static void main(String[] args) {
        int w = 10;
        int[] p = {5, 5, 3, 4, 3};
        int[] g = {400, 500, 200, 300, 350};
        System.out.println("最优收益:" + getBestGoldMining(w, g.length, p, g));
        System.out.println("最优收益:" + getBestGoldMiningV2(w, p, g));
        System.out.println("最优收益:" + getBestGoldMiningV3(w, p, g));
    }

}
