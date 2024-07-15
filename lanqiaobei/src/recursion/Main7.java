package recursion;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-18 15:35
 * @Describe 题4·最长连续递增子序列（部分有序）
 * (1,9,2,5,7,3,4,6,8,0)中最长的递增子序列为(3,4,6,8)
 */
public class Main7 {

    /**
     *
     * @param arr 待查找数组
     * @return 最长的递增子序列
     */
    static int[] subsequence(int[] arr){
        int[] sub = new int[arr.length];
        int begin = 0;
        int end = 0;
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[end]){
                end ++;
            }else {
                if ((end - begin) > count){
                    int l = end - begin;
                    sub = new int[l+1];
                    for (int j = 0; j <= l ; j++) {
                        sub[j] = arr[begin];
                        begin ++ ;
                    }
                }
                begin = i;
                end = i;
            }
            if (i == arr.length -1){
                if ((end - begin) > count){
                    int l = end - begin;
                    sub = new int[l+1];
                    for (int j = 0; j <= l ; j++) {
                        sub[j] = arr[begin];
                        begin ++ ;
                    }
                }
            }
        }
        return sub;
    }

    public static void main(String[] args) {
        int[] sub = new int[]{1,9,2,5,7,3,4,6,8,0,1,2,3,4,5,6,8};
        System.out.println(Arrays.toString(subsequence(sub)));
    }
}
