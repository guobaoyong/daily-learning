package xh_algorithm.chapter04;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-09-09 9:34
 * @description 堆排序
 * 时间复杂度O(nlogn)
 * 堆排序和快速排序的区别和联系
 * 相同点：堆排序和快速排序的平均时间复杂度都是O(nlogn)，都是不稳定排序
 * 不同点：快速排序的最坏时间复杂度是O(n^2)，而堆排序的最坏时间复杂度稳定在O(nlogn)
 *         快速排序递归和非递归的平均空间复杂度都是O(logn)，而堆排序的空间复杂度是O(1)
 */
public class Main5 {

    /**
     * 下沉调整
     * @param array 待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length 堆的有效长度
     */
    static void downAdjust(int[] array,int parentIndex,int length){
        //temp保存父节点值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2*parentIndex+1;
        while (childIndex<length){
            //如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex+1<length && array[childIndex+1]>array[childIndex]){
                childIndex++;
            }
            //如果父节点大于任何一个孩子的值，则直接跳出
            if (temp >= array[childIndex]){
                break;
            }
            //无需真正的交换，单项赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 堆排序(升序)
     * @param array 待调整的堆
     */
    public static void heapSort(int[] array) {
        //1.把无序数组构建成最大堆
        for (int i = (array.length-2)/2; i >= 0 ; i--) {
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        //2.循环删除堆顶元素，移到集合尾部，调整堆产生新的堆顶
        for (int i = array.length - 1; i > 0 ; i--) {
            //最后一个元素和第一个元素进行交换
            int temp = array [i];
            array [i] = array [0];
            array [0] = temp;
            //下沉调整最大堆
            downAdjust(array,0,i);
        }
    }

    public static void main(String[] args) {
        int [] array = new int [] {1,3,2,6,5,7,8,9,10,0};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

}
