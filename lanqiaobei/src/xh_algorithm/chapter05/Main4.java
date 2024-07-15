package xh_algorithm.chapter05;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-09-17 11:01
 * @description 判断一个数是否为2的整数次幂
 */
public class Main4 {

    /**
     * 算法时间复杂度为O(logn)
     * @param num
     * @return
     */
    static boolean isPowerOf2V1(int num){
        int temp = 1;
        while (temp <= num){
            if (temp == num){
                return true;
            }
            temp = temp*2;
        }
        return false;
    }

    /**
     * 算法时间复杂度为O(logn)
     * @param num
     * @return
     * 移位运算
     */
    static boolean isPowerOf2V2(int num){
        int temp = 1;
        while (temp <= num){
            if (temp == num){
                return true;
            }
            temp = temp << 1;
        }
        return false;
    }

    /**
     * 算法时间复杂度为O(1)
     * @param num
     * @return
     * 如果一个整数是2的整数次幂，那么当它转化成二进制，只有最高位是1，其他都是0
     * 2的整数次幂减1，二进制数全变成1
     * n&(n-1) == 0
     */
    static boolean isPowerOf2V3(int num){
        return (num & num-1) == 0;
    }


    public static void main(String[] args) {
        System.out.println(isPowerOf2V1(32));
        System.out.println(isPowerOf2V1(29));
        System.out.println("-----------------------");
        System.out.println(isPowerOf2V2(32));
        System.out.println(isPowerOf2V2(29));
        System.out.println("-----------------------");
        System.out.println(isPowerOf2V3(32));
        System.out.println(isPowerOf2V3(29));
    }
}
