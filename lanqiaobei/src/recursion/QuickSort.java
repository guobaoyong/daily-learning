package recursion;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-17 15:32
 * @Describe 快速排序
 */
public class QuickSort {

    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     *
     * @param array 待查找数组
     * @param lo   开始位置
     * @param hi  结束位置
     * @return  中轴所在位置
     */
    static int getMiddle(int []array,int lo,int hi) {
        //固定的切分方式
        int key=array[lo];
        while(lo<hi){
            //从后半部分向前扫描
            while(array[hi]>=key&&hi>lo){
                hi--;
            }
            array[lo]=array[hi];
            //从前半部分向后扫描
            while(array[lo]<=key&&hi>lo){
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }

    /**
     *
     * @param numbers 带排序数组
     * @param low  开始位置
     * @param high 结束位置
     */
    static int[] quickSort(int[] numbers,int low,int high) {
        if(low < high) {
            int middle = getMiddle(numbers,low,high); //将numbers数组进行一分为二
            quickSort(numbers, low, middle-1);   //对低字段表进行递归排序
            quickSort(numbers, middle+1, high); //对高字段表进行递归排序
        }
        return numbers;
    }

    /**
     *
     * @param array 待排序的数组
     * @param lo 开始位置
     * @param hi 结束位置
     * @return 基准值所在位置
     */
    static int partition(int []array,int lo,int hi){
        //三数取中
        int mid=lo+(hi-lo)/2;
        if(array[mid]>array[hi]){
            swap(array[mid],array[hi]);
        }
        if(array[lo]>array[hi]){
            swap(array[lo],array[hi]);
        }
        if(array[mid]>array[lo]){
            swap(array[mid],array[lo]);
        }
        int key=array[lo];

        while(lo<hi){
            while(array[hi]>=key&&hi>lo){
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }

    /**
     * 交换a，b的值
     * @param a 待交换a
     * @param b 待交换b
     */
    static void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
    }

    /**
     *
     * @param array 带排序数组
     * @param lo  开始位置
     * @param hi 结束位置
     */
    static int[] sort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return array;
        }
        int index=partition(array,lo,hi);
        sort(array,lo,index-1);
        sort(array,index+1,hi);
        return array;
    }

    /**
     * insertSort(arr,k) 递归实现插入排序
     * 找重复：insertSort(arr,k-1) 将k-1个排序后，把arr[k]插入到前面的数据中 --子问题
     * 找变化：变化的量应该作为参数 k。
     * 找边界：出口 终止的条件 k == 0
     */
    static int[] insertSort(int[] arr,int k){
        if (k == 0){
            return arr;
        }
        //对前k-1个元素排序
        insertSort(arr,k-1);
        //把k位置上的元素插入到前面的部分
        int x = arr[k];
        int index = k -1;
        while (index >= 0 && x <arr[index]){
            arr[index+1] = arr[index];
            index--;
        }
        arr[index+1] = x;
        return arr;
    }

    /**
     * 优化后的快速排序算法
     * @param array 待排序数组
     * @param lo 开始位置
     * @param hi 结束位置
     * @return 已排序的数组
     */
    static int[] quick(int []array ,int lo,int hi){
        if(hi-lo+1<10){
            return insertSort(array,array.length-1);
        }else{
            return sort(array,lo,hi);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,3,7,4,1,5,8,9,5,44,6,5};
        System.out.println(Arrays.toString(quickSort(arr,0,arr.length-1)));
        System.out.println(Arrays.toString(sort(arr,0,arr.length-1)));
        System.out.println(Arrays.toString(quick(arr,0,arr.length-1)));
    }
}
