package sort;

import com.sun.org.glassfish.external.statistics.annotations.Reset;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-24 16:51
 * @Describe
 */

public class Main {
    /**
     * 冒泡排序
     * @param array
     * @return
     */
    static int[] a(int[] array){
        if (array.length == 0){
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j+1] < array[j]){
                    int temp = array[j+1];
                    array[j+1] =array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 选择排序
     * @param array
     * @return
     */
    static int[] b(int[] array){
        if (array.length == 0){
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] <array[minIndex])
                    minIndex = j;
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] =temp;
        }
        return array;
    }

    /**
     * 插入排序
     * @param array
     * @return
     */
    static int[] c(int[] array){
        if (array.length == 0){
            return array;
        }
        int current;
        for (int i = 0; i < array.length-1; i++) {
            current = array[i+1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]){
                array[preIndex +1] = array[preIndex];
                preIndex--;
            }
            array[preIndex+1]=current;
        }
        return array;
    }

    /**
     * 希尔排序
     * @param array
     * @return
     */
    static int[] d(int[] array){
        if (array.length == 0){
            return array;
        }
        int len = array.length;
        int temp,gap = len >> 1;
        while (gap > 0){
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i-gap;
                while (preIndex >= 0 && temp <array[preIndex]){
                    array[preIndex+gap] = array[preIndex];
                    preIndex-=gap;
                }
                array[preIndex+gap]=temp;
            }
            gap = gap >> 1;
        }
        return array;
    }

    /**
     * 归并排序
     * @param array
     * @return
     */
    static int[] e(int[] array){
        if (array.length < 2)
            return array;
        int mid = array.length >> 1;
        int[] left = Arrays.copyOfRange(array,0,mid);
        int[] right = Arrays.copyOfRange(array,mid,array.length);
        return f(e(left),e(right));
    }

    /**
     * 归并排序-将两个有序的数组合并
     * @param e
     * @param e1
     * @return
     */
    static int[] f(int[] e, int[] e1) {
        int[] result = new int[e.length+e1.length];
        for (int index = 0,i=0,j=0; index < result.length ; index++) {
            if (i >= e.length)
                result[index]=e1[j++];
            else if (j >= e1.length)
                result[index]=e[i++];
            else if (e[i] > e1[j])
                result[index]=e1[j++];
            else
                result[index]=e[i++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {4,1,5,3,2};
        System.out.println(Arrays.toString(a(array)));
        System.out.println(Arrays.toString(b(array)));
        System.out.println(Arrays.toString(c(array)));
        System.out.println(Arrays.toString(d(array)));
        System.out.println(Arrays.toString(e(array)));
    }
}
