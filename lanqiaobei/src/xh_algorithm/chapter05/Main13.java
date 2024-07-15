package xh_algorithm.chapter05;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-11-07 16:16
 * @description 拓展问题2
 * 在一个无序的数组里有若干个正整数，范围是1-100，其中98个出现了偶数次，2个出现奇数次，如何找出这两个整数？
 */
public class Main13 {

    /**
     * 分治法
     * 时间复杂度O(n)和空间复杂度O(1)
     * @param array
     * @return
     */
    public static int[] find2NotRepeatNum(int[] array) {
        //用于存储2个出现奇数次的整数
        int[] result = new int[2];
        //第一次整体进行异或运算
        int xorResult = 0;
        for (int i = 0; i < array.length; i++) {
            xorResult ^= array[i];
        }
        //如果进行异或运算的结果为0，则说明输入的数组不符合题目要求
        if (xorResult == 0) {
            return null;
        }
        //确定两个整数的不同位数，以此来做分组
        int separator = 1;
        while (0 == (xorResult & separator)) {
            separator <<= 1;
        }
        //第二次进行异或运算
        for (int i = 0; i < array.length; i++) {
            if (0 == (array[i] & separator)) {
                result[0] ^= array[i];
            } else {
                result[1] ^= array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {4, 1, 2, 2, 5, 1, 4, 3};
        int[] result = find2NotRepeatNum(array);
        System.out.println(Arrays.toString(result));
    }

}
