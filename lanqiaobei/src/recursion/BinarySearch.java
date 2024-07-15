package recursion;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-16 10:11
 * @Describe 二分查找的两种实现
 */
public class BinarySearch {

    /**
     * 循环实现二分查找
     * @param arr 待查找的数组
     * @param key 待查找的数
     * @return key在数组中的索引位置
     */
    static int binary1(int[] arr,int key){
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

    /**
     * 递归实现二分查找
     * @param arr 待查找的数组
     * @param low 头指针所在位置
     * @param high 尾指针所在位置
     * @param key 待查找的数
     * @return key在数组中的索引位置
     * 算法分析：
     * 找重复：
     * 找变化量：
     * 找出口：头尾交叉 || key大于最大值 || key小于最小值
     */
    static int binary2(int[] arr,int low , int high ,int key){

        //头尾交叉 || key大于最大值 || key小于最小值，说明未找到
        if (low > high || key > arr[high] || key < arr[low]){
            return -1;
        }
        //定义middle指针位置,防止数据溢出
        int middle = (low + high) >>> 1;

        //middle所对应的值比key大，key应该在左边区域
        if (arr[middle] > key){
            binary2(arr,low,middle-1,key);
        }else if (arr[middle] < key){
            //middle所对应的值比key小，key应该在有边区域
            binary2(arr,low+1,high,key);
        }else {
            return middle;
        }

        //最后仍然没有找到，则返回-1
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,13,19,21,37,56,64,75,80,88,92};
        System.out.println(binary1(arr,21));
        System.out.println(binary1(arr,21));
    }

}
