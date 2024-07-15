package javaB_2018;

import hashMap.Main;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-23 8:27
 * @Describe 标题：方格计数
 *
 * 如图p1.png所示，在二维平面上有无数个1x1的小方格。
 *
 *
 * 我们以某个小方格的一个顶点为圆心画一个半径为1000的圆。
 * 你能计算出这个圆里有多少个完整的小方格吗？
 *
 * 思路：看第一象限，格子左上角到圆心的举例小于半径
 */
public class Main2 {
    public static void main(String[] args) {
        int count = 0;
        int r = 1000;
        for (int x = 1; x < r; x++) {
            for (int y = 1; y < r; y++) {
                if (Math.pow(x,2)+Math.pow(y,2) <= Math.pow(r,2)){
                    count++;
                }
            }
        }
        System.out.println(count*4);
    }
}
