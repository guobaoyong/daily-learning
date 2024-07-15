package 自增变量;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-25 21:58
 * @Describe 自增变量
 * https://www.cnblogs.com/zsh-blogs/p/10597980.html
 */
public class Test {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }
}
