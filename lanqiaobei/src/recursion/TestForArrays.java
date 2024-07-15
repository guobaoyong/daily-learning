package recursion;

import java.util.Arrays;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-17 9:33
 * @Describe Arrays方法测试
 */
public class TestForArrays {

    public static void main(String[] args) {
        //填充数组，将arr[]中所有元素的值初始为0
        int[] arr = new int[5];
        Arrays.fill(arr,12);
        System.out.println(Arrays.toString(arr));
        //将arr中的第2个到第三个元素的值赋为8
        Arrays.fill(arr,1,3,8);
        System.out.println(Arrays.toString(arr));
        //对数组进行排序
        int[] arr1 = new int[]{7,6,8,5,2,9,8,1,3,5};
        //对数组的第二个到第6个元素进行排序
        Arrays.sort(arr1,1,6);
        System.out.println(Arrays.toString(arr1));
        //对整个数组进行排序
        Arrays.sort(arr1);
        System.out.println(Arrays.toString(arr1));
        //比较数组元素是否相等
        System.out.println(Arrays.equals(arr,arr1));
        //使用二分法在数组中查找指定元素所在的下标
        //数组必须是先排好序的
        System.out.println(Arrays.binarySearch(arr1,5));
        //如果不存在，就返回负数
        System.out.println(Arrays.binarySearch(arr1,20));
    }


}
