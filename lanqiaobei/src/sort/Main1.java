package sort;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-24 21:03
 * @Description 冒泡排序
 */
public class Main1 {
    static int[] bubbleSort(int[] array){
        if (array.length == 0){
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j+1] > array[j]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {4,1,5,3,2};
        System.out.println(Arrays.toString(bubbleSort(array)));
    }
}
