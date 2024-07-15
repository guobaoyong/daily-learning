package xh_algorithm.chapter05;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-11-07 16:10
 * @description 拓展问题1
 * 在一个无序的数组里有若干个正整数，范围是1-100，其中99个出现了偶数次，1个出现奇数次，如何找出这个的整数？
 */
public class Main12 {

    /**
     * 异或运算，相同为0，不同为1
     * 时间复杂度O(n)和空间复杂度O(1)
     * @param array
     */
    public static void findNotRepeatNum(int[] array) {
        int result = array [0];
        for (int i = 1; i < array.length; i++) {
            result = result ^ array[i];
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] array = {3,1,3,2,4,1,4};
        findNotRepeatNum(array);
    }

}
