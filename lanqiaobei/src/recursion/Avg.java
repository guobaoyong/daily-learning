package recursion;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-16 10:39
 * @Describe a+b的平均值正确写法
 */
public class Avg {

    /**
     * 求a+b平均值
     * @param a
     * @param b
     * @return a+b的平均值
     */
    static int avg(int a ,int b){
        double random = Math.random();
        if (random >= 0 && random <= 0.25){
            //正确写法1
            return ((a&b) + ((a^b) >> 1));
        }else if (random > 0.25 && random <= 0.5){
            //正确写法2
            return b+(a-b)/2;
        }else if (random > 0.5 && random <= 0.75){
            //正确写法3
            return b+((a-b)>>1);
        }else if (random > 0.75 && random <= 1.0){
            //正确写法4
            return (a+b)>>>1;
        }else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(avg(3,5));
    }
}
