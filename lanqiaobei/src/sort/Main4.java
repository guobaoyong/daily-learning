package sort;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-25 8:24
 * @Description 希尔排序
 */
public class Main4 {
    static int[] shellSort(int[] array){
        int len = array.length;
        int temp,gap = len >> 1;
        while (gap > 0){
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i-gap;
                while (preIndex >= 0 && array[preIndex] > temp){
                    array[preIndex+gap]=array[preIndex];
                    preIndex-=gap;
                }
                array[preIndex+gap]=temp;
            }
            gap = gap >> 1;
        }
        return array;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        int[] array = {4,1,5,3,2};
        System.out.println(Arrays.toString(shellSort(array)));
        String s = String.class.newInstance();
        String hello = String.class.getConstructor(String.class).newInstance("Hello");
        System.out.println(s);
        System.out.println(hello);
    }
}
