package xh_algorithm.chapter04;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-28 9:49
 * @description 快速排序-单边循环法
 * 递归形式
 */
public class Main3 {

    /**
     * 快速排序-单边循环法
     * @param array 待排序的数组
     * @param startIndex 起始下标
     * @param endIndex 结束下标
     */
    static void quickSort(int [] array,int startIndex,int endIndex){
        //递归结束条件：startIndex大于等于endIndex时
        if (startIndex >= endIndex){
            return;
        }
        //得到基准元素位置
        int pivotIndex = partition(array,startIndex,endIndex);
        //根据基准元素，分成两部分进行递归排序
        quickSort(array, startIndex, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, endIndex);
    }

    /**
     * 分治(单边循环法)
     * @param array 待交换的数组
     * @param startIndex 起始下标
     * @param endIndex 结束下标
     * @return
     */
    static int partition(int [] array, int startIndex, int endIndex) {
        //取第1个位置（也可以选择随机位置）的元素作为基准元素
        int pivot = array [startIndex];
        int mark = startIndex;

        for (int i = startIndex+1; i <= endIndex ; i++) {
            if (array [i] < pivot){
                mark++;
                int p = array[mark];
                array [mark] = array [i];
                array[i] = p;
            }
        }

        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,4,6,5,3,2,8,1};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
