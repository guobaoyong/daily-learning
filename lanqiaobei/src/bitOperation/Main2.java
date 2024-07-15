package bitOperation;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-14 14:36
 * @Describe 题2:找出落单的那个数
 * 一个数组里除了某个数字之外的数字都出现了两次。
 * 请写程序找出这个只出现一次的数字
 */
public class Main2 {
    public static void main(String[] args) {
        //规定数组序列
        int[] arr = {1,2,3,2,1};
        //逐个异或
        int x = 0;
        for (int i = 0; i < arr.length ; i++) {
            x = x ^ arr[i];
        }
        //打印数组
        System.out.println(Arrays.toString(arr));
        System.out.println(x);
    }
}
