package xh_algorithm.chapter05;

import java.util.*;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-11-07 15:24
 * @description 缺整数
 * 在一个无序的数组里有99个不重复的正整数，范围是1-100，唯独缺少1个1-100中的整数，如何找出这个缺失的整数？
 */
public class Main11 {

    /**
     * 哈希表
     * 时间复杂度和空间复杂度都是O(n)
     * @param array 无序数组
     * @return
     */
    public static void findLostNum(int[] array) {
        //1.创建一个1-100的哈希表
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i + 1, 0);
        }
        //2.遍历数组移除map
        for (int i = 0; i < array.length; i++) {
            map.remove(array[i]);
        }
        //3.遍历map剩余的数就是缺失的数
        map.forEach((key, value) -> {
            System.out.println(key);
        });
    }

    /**
     * 先排序，再根据差值寻找不连续的数字
     * 时间复杂度O(nlogn)和空间复杂度都是O(1)
     * @param array
     */
    public static void findLostNumV2(int[] array) {
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] - array[i] != 1) {
                System.out.println(array[i] + 1);
                break;
            }
        }
    }

    /**
     * 先算1-100累加和，然后减去数组的每一个元素，剩下的就是缺失的数
     * 时间复杂度O(n)和空间复杂度O(1)
     * @param array
     */
    public static void findLostNumV3(int[] array) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        for (int i = 0; i < array.length; i++) {
            sum -= array[i];
        }
        System.out.println(sum);
    }

    /**
     * 生成1-100不重复随机序列
     *
     * @param n 个数
     * @return
     */
    public static int[] random(int n) {
        int[] arr = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100 + 1);
            if (!set.add(arr[i])) {
                i--;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        //1.生成随机序列
        int[] random = random(99);
        System.out.println(Arrays.toString(random));
        //2.调用方法寻找丢失的数
        long c1 = System.currentTimeMillis();
        findLostNum(random);
        System.out.println("运行时间：" + (System.currentTimeMillis() - c1));
        long c2 = System.currentTimeMillis();
        findLostNumV2(random);
        System.out.println("运行时间：" + (System.currentTimeMillis() - c2));
        long c3 = System.currentTimeMillis();
        findLostNumV3(random);
        System.out.println("运行时间：" + (System.currentTimeMillis() - c3));
    }

}
