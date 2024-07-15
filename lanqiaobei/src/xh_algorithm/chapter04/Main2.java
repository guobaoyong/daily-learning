package xh_algorithm.chapter04;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-21 9:02
 * @description 快速排序 -分治法
 * 双边循环法
 * 递归形式
 */
public class Main2 {

    /**
     * 快速排序-双边循环法
     * @param array 待排序数组
     * @param startIndex 头指针
     * @param endIndex 尾指针
     */
    static void quickSort(int[] array,int startIndex,int endIndex){
        //递归结束的条件：startIndex大于等于endIndex时
        if (startIndex >= endIndex){
            return;
        }
        //得到基准元素
        int privotIndex = partition(array,startIndex,endIndex);
        //根据基准元素，分成两部分进行递归排序
        quickSort(array,startIndex,privotIndex-1);
        quickSort(array,privotIndex+1,endIndex);
    }

    /**
     * 分治(双边循环法)
     * @param array 待交换的数组
     * @param startIndex 起始下标
     * @param endIndex 结束下标
     * @return
     */
    static int partition(int[] array,int startIndex,int endIndex){
        //取第1个位置(也可以选择随机位置)的元素作为基准元素
        int pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right){
            //控制right指针比较并左移
            while (left<right && array[right] > pivot){
                right--;
            }
            //控制left指针比较并右移
            while (left<right && array[left] <= pivot){
                left++;
            }
            //交换left和right指针所指向的元素
            if (left<right){
                int p = array[left];
                array[left] = array[right];
                array[right] = p;
            }
        }

        //pivot和指针重合点交换
        array[startIndex] = array[left];
        array[left] = pivot;

        return left;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,4,6,5,3,2,8,1};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
