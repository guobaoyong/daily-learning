package recursion;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-17 8:31
 * @Describe 冒泡排序实现算法
 */
public class BubbleSort {

    /**
     * 冒泡排序
     * @param arr 待排序的数组
     * @return 已排序的数组
     */
    static int[] bubbleSort(int[] arr){
        //外层循环执行N-1趟
        for (int i = 0; i < arr.length -1; i++) {
            //内层循环执行N-1-i趟
            for (int j = 0; j < arr.length -1 -i ; j++) {
                //如果数组前一个元素比后一个元素的值大，交换
                if (arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,3,8,2,9,1};
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }
}
