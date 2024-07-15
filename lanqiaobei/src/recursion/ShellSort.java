package recursion;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-16 15:14
 * @Describe 希尔排序实现
 */
public class ShellSort {

    /**
     * 希尔排序实现方法
     * @param arr 待排序的数组
     * @return 排序过的数组
     * 一趟一个增量，用增量来分组，组内执行插入排序
     */
    static int[] shellSort(int[] arr){
        //不断的减小增量 增量位incrementNum/2
        for (int incrementNum = arr.length / 2 ; incrementNum > 0 ; incrementNum = incrementNum /2) {
            for (int i = incrementNum; i < arr.length; i++) {
                //待插入的数
                int target = arr[i];
                //待插入数索引的前增量个数的增量
                int j = i - incrementNum;
                while (j >-1 && target < arr[j]){
                    arr[j+incrementNum] = arr[j];
                    j = j - incrementNum;
                }
                arr[j+incrementNum] = target;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{82 ,31 ,29 ,71, 72, 42, 64, 5,110};
        System.out.println(Arrays.toString(shellSort(arr)));
    }
}
