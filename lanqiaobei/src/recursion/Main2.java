package recursion;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-16 16:09
 * @Describe 感受顺序查找与二分查找的性能差别
 */
public class Main2 {

    /**
     * 顺序查找
     * @param arr 待查找的数组
     * @param key 待查找的数
     * @return 返回key在数组中所在的位置
     */
    static int sequentialSearch(int[] arr,int key){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key){
                return i;
            }
        }
        return -1;
    }

    /**
     * 循环实现二分查找
     * @param arr 待查找的数组
     * @param key 待查找的数
     * @return key在数组中的索引位置
     */
    static int binary(int[] arr,int key){
        //头指针初始位置
        int low = 0;
        //尾指针初始位置
        int high = arr.length -1;
        //定义middle指针位置
        int middle = 0;
        //头尾交叉 || key大于最大值 || key小于最小值，说明未找到
        if (low > high || key > arr[high] || key < arr[low]){
            return -1;
        }

        while (low <= high){
            //防止数据溢出
            middle = (low + high) >>> 1;
            if (arr[middle] > key){
                //middle所对应的值比key大，key应该在左边区域
                high = middle -1;
            }else if (arr[middle] < key){
                //middle所对应的值比key小，key应该在有边区域
                low = middle +1;
            }else {
                return middle;
            }

        }

        //最后仍然没有找到，则返回-1
        return -1;
    }

    public static void main(String[] args) {
        //构造1千万的数据
        int[] arr = new int[(int) Math.pow(10,8)];
        for (int i = 1; i <= (int) Math.pow(10,8) ; i++) {
            arr[i-1] = i;
        }

        //使用顺序查找的运行时间
        long time1 = System.currentTimeMillis();
        sequentialSearch(arr,1000000);
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);

        //使用二分查找的运行时间
        long time3 = System.currentTimeMillis();
        binary(arr,1000000);
        long time4 = System.currentTimeMillis();
        System.out.println(time4-time3);
    }

}
