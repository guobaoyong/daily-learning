package xh_algorithm.chapter04;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-21 8:40
 * @description 冒泡排序
 */
public class Main1 {

    /**
     * 冒泡排序第一版
     * @param array
     */
    static void sort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                int temp = 0;
                if (array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序第二版 加入有序标记
     * @param array
     */
    static void sort2(int[] array){
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初值都为true
            boolean isSorted = true;
            for (int j = 0; j < array.length-i-1; j++) {
                int temp = 0;
                if (array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //因为有元素交换，所以isSorted设为false
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }
        }
    }

    /**
     * 冒泡排序第三版
     * @param array 加入对数组有序区的界定
     */
    static void sort3(int[] array){
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数组的边界，每次比较只需要比到这里为止
        int sortBorder = array.length-1;
        for (int i = 0; i < array.length-1; i++) {
            //有序标记，每一轮的初值都为true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                int temp = 0;
                if (array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //因为有元素交换，所以isSorted设为false
                    isSorted = false;
                    //更新最后一次交换的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted){
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序 双向比较
     * @param array
     */
    static void sort4(int[] array){
        int temp = 0;
        //array.length/2 <-> array.length>>1
        for (int i = 0; i < array.length>>1; i++) {
            //有序标记，每一轮的初值都为true
            boolean isSorted = true;
            //奇数轮，从左到右比较交换
            for (int j = i; j < array.length-i-1; j++) {
                if (array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //因为有元素交换，所以isSorted设为false
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }
            //偶数轮之前，将isSorted标记为true
            isSorted = true;
            //偶数轮，从右往左比较和交换
            for (int j = array.length-i-1; j >i ; j--) {
                if (array[j] < array[j-1]){
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    //因为有元素交换，所以isSorted设为false
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,8,6,3,9,2,1,7};
        /*sort(array);*/
        /*sort2(array);*/
        /*sort3(array);*/
        sort4(array);
        System.out.println(Arrays.toString(array));
    }
}
