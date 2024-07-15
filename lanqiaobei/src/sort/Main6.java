package sort;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-26 16:10
 * @Description 快速排序
 */
public class Main6 {

    /**
     * 快速排序方法
     * @param array
     * @param start
     * @param end
     * @return
     */
    static int[] quickSort(int[] array,int start,int end){
        if (array.length<1 || start <0 || end>=array.length || start >end)
            return array;
        int smallIndex = partition(array,start,end);
        if (smallIndex > start)
            quickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            quickSort(array, smallIndex + 1, end);
        return array;
    }

    /**
     * 快速排序算法——partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }

    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    static void swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {4,1,5,3,2};
        System.out.println(Arrays.toString(quickSort(array,0,array.length-1)));
    }
}
