package synchronizedTest;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-08 21:48
 * @Describe 可重入粒度测试：递归调用本方法
 */
public class SynchronizedRecursion10 {
    int a = 0;

    public static void main(String[] args) {
        SynchronizedRecursion10 synchronizedRecursion10 = new SynchronizedRecursion10();
        synchronizedRecursion10.method1();
    }

    private synchronized void method1() {
        System.out.println("这是method1方法，a="+a);
        if (a == 0){
            a++;
            method1();
        }
    }
}
