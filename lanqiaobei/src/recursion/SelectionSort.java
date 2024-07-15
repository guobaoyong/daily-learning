package recursion;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-17 8:47
 * @Describe 选择排序算法实现
 */
public class SelectionSort {

    /**
     * 选择排序
     * @param arr 待排序的数组
     * @return 已排序的数组
     */
    static int[] selectionSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int k = i;
            for (int j = i + 1; j < arr.length ; j++) {
                if (arr[j] < arr[k]){
                    //记录此时找到最小值的位置
                    k = j;
                }
            }
            //内层循环结束，找到最小值后进行交换
            if (i != k){
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,2,8,4,9,1};
        System.out.println(Arrays.toString(selectionSort(arr)));
        Arrays.sort(arr);
        System.out.println(Arrays.binarySearch(arr,1));
    }
}
