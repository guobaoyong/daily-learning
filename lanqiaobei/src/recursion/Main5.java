package recursion;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-18 10:49
 * @Describe 题2·旋转数组的最小数字(改造二分法)
 * 把一个数组最开始的作干个元素搬到数组末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组[3,4,5,1,2]为[1,2,3,4,5]的一个旋转，
 * 该数组的最小值为1。
 */
public class Main5 {

    /**
     * 查找数组最小值
     * @param arr 待查找的数组
     * @return 最小值
     */
    static int min(int[] arr){
        //头指针
        int begin = 0;
        //尾指针
        int end = arr.length -1;
        //考虑到没有旋转这种特殊的旋转
        if (arr[begin] < arr[end])
            return arr[begin];
        //begin与end指向相邻元素，退出
        while (begin +1 <end){
            int middle = ((begin + end) >>> 1);
            //注意特殊情况
            //如果arr[begin] == arr[middle] == arr[end] ，采用扫描法
            if (arr[begin] == arr[end] && arr[begin] == arr[middle] && arr[middle] == arr[end]){
                int min = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] < min){
                        min = arr[i];
                    }
                }
                return min;
            }
            //左边有序
            if (arr[middle] >= arr[begin]){
                begin = middle;
            }else {
                //右边有序
                end = middle;
            }
        }
        return arr[end];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,1,2,3,4};
        System.out.println(min(arr));
        arr = new int[]{2,3,4,5,6};
        System.out.println(min(arr));
        arr = new int[]{1,0,1,1,1};
        System.out.println(min(arr));
    }
}
