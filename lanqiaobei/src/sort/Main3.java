package sort;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-24 21:30
 * @Description 插入排序
 */
public class Main3 {
    static int[] insertionSort(int[] array){
        if (array.length ==0){
            return array;
        }
        int current;
        for (int i = 0; i < array.length-1; i++) {
            current = array[i+1];
            int preIndex = i;
            while (preIndex >= 0 && current <array[preIndex]){
                array[preIndex+1] = array[preIndex];
                preIndex--;
            }
            array[preIndex+1] = current;
        }
        return array;
    }
    public static void main(String[] args) {
        int[] array = {4,1,5,3,2};
        System.out.println(Arrays.toString(insertionSort(array)));
    }
}
