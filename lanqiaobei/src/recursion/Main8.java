package recursion;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-18 16:30
 * @Describe 设计一个高效的求a的n次幂的算法
 */
public class Main8 {

    /**
     * 循环法求a的n次幂，复杂度O(n)
     * @param a
     * @param n
     * @return 结果
     */
    static int pow0(int a,int n){
        int res = 1;
        for (int i = 0; i < n; i++) {
            res = res*a;
        }
        return res;
    }

    /**
     * 优化后求a的n次幂
     * @param a
     * @param n
     * @return 结果
     */
    static int pow1(int a,int n){
        if (n == 0){
            return 1;
        }
        int res = a;
        //指数
        int ex = 1;
        while ( 2*ex <= n){
            res = res*res;
            ex = ex*2;
        }
        //差n-ex次方没有乘到结果上去
        return res*pow1(a,n-ex);
    }

    public static void main(String[] args) {
        System.out.println(pow0(2,15));
        System.out.println(pow1(2,15));
    }
}
