package xh_algorithm.chapter02;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-13 15:08
 * @description 数组的二分查找
 * 待查证的数组必须是有序的
 */
public class Main2 {

    /**
     * 二分查找
     * @param array 待查找的数组
     * @param number 待查找的值
     * @return
     */
    static int halfSearch(int[] array,int number){
        //定义3个变量，用来记录min，max，mid指针的位置
        int min = 0;
        int max = array.length-1;
        int mid;
        while (min <= max){
            mid = (min+max)>>1;
            //在中位值的左边
            if (array[mid] > number){
                max = mid -1;
            }else if (array[mid] < number){
                min = mid +1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,4,7,9,12,15,20};
        System.out.println(halfSearch(array,7));
    }
}
