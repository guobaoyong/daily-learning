package xh_algorithm.chapter04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-28 10:29
 * @description 快速排序-非递归形式
 * 栈实现
 */
public class Main4 {

    /**
     * 快速排序-非递归形式-栈实现
     * @param array 待排序的数组
     * @param startIndex 起始下标
     * @param endIndex 结束下标
     */
    static void quickSort(int [] array,int startIndex, int endIndex){
        //用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<> ();
        //整个数列的起止下标，以哈希的形式入栈
        Map rootParam = new HashMap();
        rootParam.put("startIndex",startIndex);
        rootParam.put("endIndex", endIndex);
        quickSortStack.push(rootParam);

        //循环结束条件：栈为空时
        while (!quickSortStack.isEmpty()) {
            //栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickSortStack.pop();
            //得到基准元素位置
            int pivotIndex = partition(array, param.get("startIndex"), param.get("endIndex"));
            //根据基准元素分成两部分，把每一部分的起止下标入栈
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if (pivotIndex + 1 < param.get("endIndex")) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
    }

    /**
     * 分治(单边循环法)
     * @param array 待交换的数组
     * @param startIndex 起始下标
     * @param endIndex 结束下标
     * @return
     */
    static int partition(int [] array, int startIndex,int endIndex){
        //取第1个位置（也可以选择随机位置）的元素作为基准元素
        int pivot = array [startIndex];
        int mark = startIndex;

        for (int i = startIndex+1; i <= endIndex ; i++) {
            if (array [i] < pivot){
                mark++;
                int p = array[mark];
                array [mark] = array [i];
                array[i] = p;
            }
        }

        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,7,6,5,3,2,8,1};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
