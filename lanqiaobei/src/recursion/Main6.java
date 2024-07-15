package recursion;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-18 15:21
 * @Describe 题3·在有空字符串的有序字符串数组中查找
 * 有个排序后的字符串数组，其中散布着一些空字符串，
 * 编写一个方法，找出给定字符串（肯定不是空字符串）的索引。
 */
public class Main6 {

    /**
     * 查找给定字符串（肯定不是空字符串）的索引
     * @param arr 待查找的字符数组
     * @param p 待查找的字符串
     * @return 字符串在数组中的位置索引
     */
    static int indexOf(String[] arr,String p){
        int begin = 0;
        int end = arr.length-1;
        while (begin <= end){
            int middle = ((begin+end) >>> 1);
            while (arr[middle].equals("")){
                middle++;
                //注意
                if (middle > end){
                    return -1;
                }
            }
            if (arr[middle].compareTo(p) > 0){
                end = middle -1;
            }else if (arr[middle].compareTo(p) < 0){
                begin = middle + 1;
            }else {
                return middle;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"a","","ac","","ad","b","","ba"};
        System.out.println(indexOf(arr,"b"));
        System.out.println(indexOf(arr,"abc"));
    }
}
