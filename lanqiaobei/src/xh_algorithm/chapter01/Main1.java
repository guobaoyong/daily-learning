package xh_algorithm.chapter01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-13 11:20
 * @description 找出数组中重复的两个数
 */
public class Main1 {

    /**
     * 时间复杂度O(n^2)
     * @param array
     * @return
     */
    static int f1(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] == array[j]){
                    return array[i];
                }
            }
        }
        return -1;
    }

    /**
     * 散列O(n)
     * @param array
     * @return
     */
    static int f2(int[] array){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.get(array[i]) != null){
                return array[i];
            }else {
                map.put(array[i],1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {3,1,2,5,4,9,7,2};
        System.out.println(f2(array));
    }

}
