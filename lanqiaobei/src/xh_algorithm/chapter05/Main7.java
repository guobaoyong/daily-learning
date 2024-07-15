package xh_algorithm.chapter05;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-11-06 15:57
 * @description 寻找全排列的下一个数
 * 在一个整数所包含数字的全部组合中，找到一个大于且仅大于原数的新整数
 * 实现步骤：
 * 1.从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
 * 2.让逆序区域的前一位和逆序区域中大于它的最小的数字交换位置
 * 3.把原来的逆序区域转为顺序状态
 * 字典序算法
 */
public class Main7 {

    public static int[] findNearestNumber(int[] numbers) {
        //1.从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
        int index = findTransferPoint(numbers);
        //如果数字的置换边界为0，说明整个数组已经逆序，无法获得更大的相同数字组成的整数，返回null
        if (index == 0) {
            return null;
        }
        //2.让逆序区域的前一位和逆序区域中大于它的最小的数字交换位置
        //复制并入参，避免直接修改入参
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        exchangeHead(numbersCopy, index);
        //把原来的逆序区域转为顺序状态
        reverse(numbersCopy,index);
        return numbersCopy;
    }

    /**
     * 找出边界
     *
     * @param numbers
     * @return
     */
    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 交换
     *
     * @param numbers
     * @param index
     * @return
     */
    private static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     * 逆序转为顺序
     * @param num
     * @param index
     * @return
     */
    private static int[] reverse(int[] num, int index) {
        for (int i = index, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
        return num;
    }

    /**
     * 输出数组
     * @param numbers
     */
    private static void outputNumbers(int[] numbers){
        for (int i : numbers) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numbers = {
            1,2,3,4,5
        };
        //打印12345之后的10个全排列整数
        for (int i = 0; i < 10; i++) {
            numbers = findNearestNumber(numbers);
            outputNumbers(numbers);
        }
    }
}
