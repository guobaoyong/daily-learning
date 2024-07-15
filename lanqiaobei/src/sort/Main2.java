package sort;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-24 21:17
 * @Description 选择排序
 */
public class Main2 {

    static int[] selectionSort(int[] array){
        if (array.length == 0){
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int mainIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] <array[mainIndex]){ //找到最小的数
                    mainIndex = j;
                }
            }
            //交换最小的数与最小数索引的值
            int temp = array[mainIndex];
            array[mainIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {4,1,5,3,2};
        System.out.println(Arrays.toString(selectionSort(array)));
    }
}
