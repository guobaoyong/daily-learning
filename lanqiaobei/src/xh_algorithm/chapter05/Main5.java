package xh_algorithm.chapter05;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-09-17 11:13
 * @description 无序数组排序后的最大相邻差
 */
public class Main5 {

    /**
     * 排序、遍历、求最大相邻差
     * @param array
     * @return
     */
    static int getMaxSortedArray(int[] array){
        Arrays.sort(array);
        int max = 0;
        for (int i = 1; i < array.length; i++) {
            if (max < array[i] - array[i-1]){
                max = array[i] - array[i-1];
            }
        }
        return max;
    }

    /**
     * 利用计数排序的算法思想
     * @param array
     * @return
     */
    static int getMaxSortedArrayV2(int[] array){
        //1.确定数组长度k和偏移量d
        int min = array[0];
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array [i] > max){
                max = array[i];
            }
            if (array [i] < min){
                min = array [i];
            }
        }
        int k = max - min + 1;
        int d = min;

        //2.创建数组
        int[] array2 = new int [k];
        for (int i = 0; i < array.length; i++) {
            array2 [array[i]-d]++;
        }

        //3.判断0连续出现的次数，计算最大差
        int maxIndex = 0;
        for (int i = 0; i < array2.length; i++) {
            if (array2 [i] == 0){
                int j = i;
                while (array2[j] == 0){
                    j++;
                }
                if (maxIndex < (j-i+1)){
                    maxIndex = (j-i+1);
                }
            }
        }
        return maxIndex;
    }

    /**
     * 桶排序的思想
     * @param array
     * @return
     */
    static int getMaxSortedArrayV3(int[] array){

        //1.得到数列的最大值和最小值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array [i] > max){
                max = array[i];
            }
            if (array [i] < min){
                min = array [i];
            }
        }
        int d = max - min;
        //如果max和min相等，说明数组所有元素都相等，返回0
        if (d == 0){
            return 0;
        }

        //2.初始化桶
        int bucketNum = array.length;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }

        //3.遍历原始数组，确定每个桶的最大最小值
        for (int i = 0; i < array.length; i++) {
            //确定数组元素所归属的桶下标
            int index = ((array[i] - min) * (bucketNum-1) /d);
            if (buckets[index].min == null || buckets[index].min > array [i]){
                buckets[index].min = array[i];
            }
            if (buckets[index].max == null || buckets[index].max < array [i]){
                buckets[index].max = array[i];
            }
        }

        //4.遍历桶，找到最大差值
        int leftMax = buckets[0].max;
        int maxDistance = 0;
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i].min == null){
                continue;
            }
            if (buckets [i].min - leftMax > maxDistance){
                maxDistance = buckets[i].min - leftMax;
            }
            leftMax = buckets[i].max;
        }

        return maxDistance;
    }

    /**
     * 桶
     */
    private static class Bucket{
        Integer min;
        Integer max;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,6,3,4,5,10,9};
        System.out.println(getMaxSortedArray(array));
        System.out.println(getMaxSortedArrayV2(array));
        System.out.println(getMaxSortedArrayV3(array));
    }
}
