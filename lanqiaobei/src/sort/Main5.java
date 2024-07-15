package sort;

import java.util.Arrays;
import java.util.logging.Level;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-25 16:31
 * @Description 归并排序
 */
public class Main5 {

    /**
     * 归并排序
     * @param array
     * @return
     */
    static int[] mergeSort(int[] array){
        if (array.length < 2)
            return array;
        int mid = array.length >> 1;
        int[] left = Arrays.copyOfRange(array,0,mid);
        int[] right = Arrays.copyOfRange(array,mid,array.length);
        return merge(mergeSort(left),mergeSort(right));
    }

    /**
     * 归并排序-将两端排序好的数组结合成一个排序数组
     * @param left
     * @param right
     * @return
     */
    static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length+right.length];
        for (int index = 0,i=0,j=0; index <result.length ; index++) {
            if (i >= left.length)
                result[index]=right[j++];
            else if (j >= right.length)
                result[index]=left[i++];
            else if (left[i] > right[j])
                result[index]=right[j++];
            else
                result[index]=left[i++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {4,1,5,3,2};
        System.out.println(Arrays.toString(mergeSort(array)));
    }
}
