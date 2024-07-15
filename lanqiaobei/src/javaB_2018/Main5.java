package javaB_2018;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-23 9:39
 * @Describe 标题：快速排序
 *
 * 以下代码可以从数组a[]中找出第k小的元素。
 * 它使用了类似快速排序中的分治算法，期望时间复杂度是O(N)的。
 * 请仔细阅读分析源码，填写划线部分缺失的内容。
 */
import java.util.Random;
public class Main5 {
    public static int quickSelect(int a[], int l, int r, int k) {
        Random rand = new Random();
        int p = rand.nextInt(r - l + 1) + l;
        int x = a[p];
        int tmp = a[p]; a[p] = a[r]; a[r] = tmp;
        int i = l, j = r;
        while(i < j) {
            while(i < j && a[i] < x) i++;
            if(i < j) {
                a[j] = a[i];
                j--;
            }
            while(i < j && a[j] > x) j--;
            if(i < j) {
                a[i] = a[j];
                i++;
            }
        }
        a[i] = x;
        p = i;
        if(i - l + 1 == k) return a[i];
        if(i - l + 1 < k) return quickSelect(a,i,r,k); //填空
        else return quickSelect(a, l, i - 1, k);
    }
    public static void main(String args[]) {
        int [] a = {1, 4, 2, 8, 5, 7};
        System.out.println(quickSelect(a, 0, 5, 4));
    }
}
