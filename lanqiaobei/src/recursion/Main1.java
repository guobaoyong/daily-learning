package recursion;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-15 16:08
 * @Describe 递归练习
 */
public class Main1 {
    public static void main(String[] args) {
        System.out.println("------------1、求n的阶乘----------------");
        System.out.println(f1(3));
        System.out.println("------------2、打印i到j-----------------");
        f2(1,3);
        System.out.println("---------3、对arr所有元素求和-----------");
        System.out.println(f3(new int[]{1,2,3,4,5},0));
        System.out.println("------------4、翻转字符串---------------");
        String str = "hello";
        System.out.println(reverse(str,str.length()-1));
        System.out.println("-----------5、斐波拉契数列--------------");
        System.out.println(fib(5));
        System.out.println("-----------6、最大公约数----------------");
        System.out.println(gcd(10,6));
        System.out.println("--------7、递归实现插入排序-----+-------");
        int[] arr = new int[]{5,4,9,6,3};
        System.out.println(Arrays.toString(insertSort(arr,arr.length-2)));
    }

    /**
     * f1(n)求n的阶乘 -->f(n-1)求n-1的阶乘
     * 找重复：n*(n-1)！，求(n-1)！是原问题的重复(规模更小) --子问题
     * 找变化：变化的量应该作为参数
     * 找边界：出口
     */
    static int f1(int n){
        if (n == 1)
            return 1;
        return n*f1(n-1);
    }

    /**
     * f2(i,j)打印i到j
     * 找重复：打印i，f2（i+1，j） --子问题
     * 找变化：变化的量应该作为参数
     * 找边界：出口 终止的条件
     */
    static void f2(int i,int j){
        if (i>j){
            return;
        }
        System.out.println(i);
        f2(i+1,j);
    }

    /**
     * f3(arr,begin) 对arr所有元素求和
     * 找重复：arr[begin] + f3(arr,begin+1) --子问题
     * 找变化：变化的量应该作为参数 begin作为变化的量,相当于头指针。
     * 找边界：出口 终止的条件 头指针与数组最后一个元素的索引相同
     */
    static int f3(int[] arr,int begin){
        if (begin == arr.length-1){
            return arr[begin];
        }
        return arr[begin] + f3(arr,begin+1);
    }

    /**
     * reverse(str,end) 翻转字符串
     * 找重复：str.charAt(end) + reverse(str,end-1) --子问题
     * 找变化：变化的量应该作为参数 end作为变化的量,相当于尾指针。
     * 找边界：出口 终止的条件 end == 0
     */
    static String reverse(String str,int end){
        if (end == 0){
            return ""+str.charAt(0);
        }
        return str.charAt(end) + reverse(str,end-1);
    }

    /**
     * fib(n) 斐波拉契数列 1 1 2 3 5 8 .....
     * 找重复：fib(n) = fib(n-1) + fib(n -2) --子问题
     * 找变化：变化的量应该作为参数 n。
     * 找边界：出口 终止的条件 n == 1 || n == 2
     */
    static int fib(int n){
        if (n == 1 || n == 2){
            return 1;
        }else {
            return fib(n-1)+fib(n-2);
        }
    }

    /**
     * gcd(m,n) 最大公约数 辗转相除法
     * 找重复：gcd(m,n) = gcd(n,m%n) --子问题
     * 找变化：变化的量应该作为参数 n。
     * 找边界：出口 终止的条件 n == 0
     */
    static int gcd(int m,int n){
        if (n == 0){
            return m;
        }
        return gcd(n,m%n);
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
}
