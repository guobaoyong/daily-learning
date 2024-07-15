package bitOperation;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-14 10:12
 * @Describe 题目·找出唯一成对的数
 * 1-1000这1000个数放在含有1001个元素的数组中,
 * 只有唯一的个元素值重复,其它均只出现一次。
 * 每个数组元素只能访问一次,设计一个算法,将它找出来;
 * 不用辅助存储空间,能否设计一个算法实现?
 */
public class Main1 {
    public static void main(String[] args) {
        //总数N
        int N = 11;
        int[] arr = new int[N];
        //生成1-1000的随机数，范围[0,N)
        int index = new Random().nextInt(N-1)+1;
        //给数组的元素赋值
        for (int i = 0; i < arr.length-1; i++) {
            arr[i]=i+1;
        }
        //最后一个数，是随机数
        arr[arr.length-1] = index;
        //打印数组
        System.out.println(Arrays.toString(arr));
        int x1 = 0;
        for (int i = 1; i < N; i++) {
            x1 = x1 ^ i;
        }
        for (int i = 0; i < N; i++) {
            x1 = x1 ^ arr[i];
        }
        System.out.println(x1);

        System.out.println("---------------使用辅助空间实现--------------------");
        int[] helper = new int[N];
        for (int i = 0; i < N; i++) {
            //将每个数出现的数量放在0-1000个元素中
            helper[arr[i]]++;
        }
        for (int i = 0; i < N; i++) {
            if (helper[i] == 2){
                System.out.println(i);
            }
        }

    }
}
