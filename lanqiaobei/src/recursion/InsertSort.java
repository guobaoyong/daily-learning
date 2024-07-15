package recursion;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-15 20:59
 * @Describe 插入排序两种实现方法
 */
public class InsertSort {

    //for循环实现插入排序
    static int[] insertSort1(int[] arr){
        //最外层指针从1开始
        for (int index = 1; index < arr.length; index++) {
            //存指针取出来的数据
            int temp = arr[index];
            //指针前一位索引
            int leftindex = index - 1;
            while (leftindex >= 0 && arr[leftindex] > temp ){
                arr[leftindex+1] = arr[leftindex];
                leftindex--;
            }
            //把temp放在空位上
            arr[leftindex+1] = temp;
        }
        return arr;
    }

    //递归实现插入排序
    static int[] insertSort2(int[] arr,int k){
        if (k == 0){
            return arr;
        }
        //对前k-1个元素排序
        insertSort2(arr,k-1);
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

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,9,6,3};
        System.out.println(Arrays.toString(insertSort1(arr)));
        System.out.println(Arrays.toString(insertSort2(arr,arr.length-2)));
    }

}
