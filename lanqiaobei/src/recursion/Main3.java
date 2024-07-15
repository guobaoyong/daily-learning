package recursion;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-17 9:46
 * @Describe 冒泡、插入、选择排序与Arrays.sort()的性能差别
 */
public class Main3 {

    /**
     * 冒泡排序
     * @param arr 待排序的数组
     * @return 已排序的数组
     */
    static int[] bubbleSort(int[] arr){
        //外层循环执行N-1趟
        for (int i = 0; i < arr.length -1; i++) {
            //内层循环执行N-1-i趟
            for (int j = 0; j < arr.length -1 -i ; j++) {
                //如果数组前一个元素比后一个元素的值大，交换
                if (arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
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
     * 选择排序
     * @param arr 待排序的数组
     * @return 已排序的数组
     */
    static int[] selectionSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int k = i;
            for (int j = i + 1; j < arr.length ; j++) {
                if (arr[j] < arr[k]){
                    //记录此时找到最小值的位置
                    k = j;
                }
            }
            //内层循环结束，找到最小值后进行交换
            if (i != k){
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        //构造随机数组
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            double a = Math.random()*10000;
            arr[i] = (int) a;
        }
        //调用冒泡排序进行
        long time1 = System.currentTimeMillis();
        System.out.println(Arrays.toString(bubbleSort(arr)));
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1+"ms");
        //调用插入排序进行(使用递归)
        long time3 = System.currentTimeMillis();
        System.out.println(Arrays.toString(insertSort(arr,arr.length-1)));
        long time4 = System.currentTimeMillis();
        System.out.println(time4-time3+"ms");
        //调用选择排序进行
        long time5 = System.currentTimeMillis();
        System.out.println(Arrays.toString(selectionSort(arr)));
        long time6 = System.currentTimeMillis();
        System.out.println(time6-time5+"ms");
        //调用Arrays.sort()进行
        long time7 = System.currentTimeMillis();
        Arrays.sort(arr);
        long time8 = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println(time8-time7+"ms");
    }

}
