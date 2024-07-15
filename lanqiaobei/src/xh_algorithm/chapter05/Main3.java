package xh_algorithm.chapter05;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-09-16 16:37
 * @description 求最大公约数
 */
public class Main3 {

    /**
     * 暴力法求最大公约数
     * @param a
     * @param b
     * @return
     */
    static int getGreatestCommonDivisor(int a,int b){
        int big = a>b?a:b;
        int small = a<b?a:b;
        if (big%small ==0){
            return small;
        }
        for (int i = small/2; i > 1 ; i--) {
            if (small % i ==0 && big % i ==0){
                return i;
            }
        }
        return 1;
    }

    /**
     * 辗转相除法/欧几里得算法
     * @param a
     * @param b
     * @return
     * 两个正整数a和b（a>b），它们的最大公约数等于a除以b的余数c和b之间的最大公约数
     */
    static int getGreatestCommonDivisorV2(int a, int b){
        int big = a>b?a:b;
        int small = a<b?a:b;
        if (big%small ==0){
            return small;
        }
        return getGreatestCommonDivisorV2(big%small, small);
    }

    /**
     * 更相减损术
     * @param a
     * @param b
     * @return
     */
    static int getGreatestCommonDivisorV3(int a, int b){
        if (a == b){
            return a;
        }
        int big = a>b?a:b;
        int small = a<b?a:b;
        return getGreatestCommonDivisorV3(big-small, small);
    }

    /**
     * 更相减损术与移位相结合
     * @param a
     * @param b
     * @return
     * 当a和b均为偶数时，gcd(a,b)=2*gcd(a/2,b/2)=2*gcd(a>>1,b>>1)
     * 当a为偶数，b为奇数时，gcd(a,b)=gcd(a/2,b)=gcd(a>>1,b)
     * 当a为奇数，b为偶数时，gcd(a,b)=gcd(a,b/2)=gcd(a,b>>1)
     * 当a和b均为奇数时，先利用更相减损术运算一次，gcd(a,b)=gcd(b,a-b),此时a-b必为偶数，然后又可以继续进行移位运算
     */
    static int getGreatestCommonDivisorV4(int a, int b){
        if (a == b){
            return a;
        }
        if ((a&1)==0 && (b&1) == 0){
            return getGreatestCommonDivisorV4(a>>1,b>>1)<<1;
        }else if ((a&1)==0 && (b&1) != 0){
            return getGreatestCommonDivisorV4(a>>1,b);
        }else if ((a&1)!=0 && (b&1) == 0){
            return getGreatestCommonDivisorV4(a,b>>1);
        }else {
            int big = a>b?a:b;
            int small = a<b?a:b;
            return getGreatestCommonDivisorV3(big-small, small);
        }
    }

    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisor(25,5));
        System.out.println(getGreatestCommonDivisor(100,80));
        System.out.println(getGreatestCommonDivisor(27,14));
        System.out.println("--------------------------------------");
        System.out.println(getGreatestCommonDivisorV2(25,5));
        System.out.println(getGreatestCommonDivisorV2(100,80));
        System.out.println(getGreatestCommonDivisorV2(27,14));
        System.out.println("--------------------------------------");
        System.out.println(getGreatestCommonDivisorV3(25,5));
        System.out.println(getGreatestCommonDivisorV3(100,80));
        System.out.println(getGreatestCommonDivisorV3(27,14));
        System.out.println("--------------------------------------");
        System.out.println(getGreatestCommonDivisorV3(25,5));
        System.out.println(getGreatestCommonDivisorV3(100,80));
        System.out.println(getGreatestCommonDivisorV3(27,14));
        System.out.println("--------------------------------------");
        System.out.println(getGreatestCommonDivisorV4(25,5));
        System.out.println(getGreatestCommonDivisorV4(100,80));
        System.out.println(getGreatestCommonDivisorV4(27,14));
    }
}
