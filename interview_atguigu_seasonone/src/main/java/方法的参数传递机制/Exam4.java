package 方法的参数传递机制;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-27 9:37
 * @Describe 方法的传递机制
 * （1）形参是基本数据类型的
 * 传递数据值
 * （2）形参是引用数据类型的
 * 传递地址值
 * 特殊的类型：String、包装类等对象不可变性
 */
public class Exam4 {
    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 2;
        int[] arr = {1, 2, 3, 4, 5};
        MyData my = new MyData();
        change(i, str, num, arr, my);
        System.out.println("i = " + i);
        System.out.println("str = " + str);
        System.out.println("num = " + num);
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("my.a = " + my.a);
    }

    public static void change(int i, String str, Integer num, int[] arr, MyData my) {
        i += 1;
        str += "world";
        num += 1;
        arr[0] += 1;
        my.a += 1;
    }
}

class MyData {
    int a = 10;
}
