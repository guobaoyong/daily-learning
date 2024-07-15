package exception;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-01 16:35
 * @Describe 异常测试
 */
public class Main2 {

    /**
     * 捕捉throw语句抛出的“除数为0”异常。
     */
    static void f1(){
        int a = 6;
        int b = 0;
        // try监控区域
        try {
            // 通过throw语句抛出异常
            if (b == 0) throw new ArithmeticException();
            System.out.println("a/b的值是：" + a / b);
        }
        // catch捕捉异常
        catch (ArithmeticException e) {
            System.out.println("程序出现异常，变量b不能为0。");
        }
        System.out.println("程序正常结束。");
    }

    /**
     * 捕捉运行时系统自动抛出“除数为0”引发的ArithmeticException异常。
     */
    static void f2(){
        int a = 6;
        int b = 0;
        try {
            System.out.println("a/b的值是：" + a / b);
        } catch (ArithmeticException e) {
            System.out.println("程序出现异常，变量b不能为0。");
        }
        System.out.println("程序正常结束。");
    }

    /**
     * 不捕捉、也不声明抛出运行时异常。
     */
    static void f3(){
        int a, b;
        a = 6;
        b = 0; // 除数b 的值为0
        System.out.println(a / b);
    }

    /**
     * 程序可能存在除数为0异常和数组下标越界异常。
     */
    static void f4(){
        int[] intArray = new int[3];
        try {
            for (int i = 0; i <= intArray.length; i++) {
                intArray[i] = i;
                System.out.println("intArray[" + i + "] = " + intArray[i]);
                System.out.println("intArray[" + i + "]模 " + (i - 2) + "的值:  "
                        + intArray[i] % (i - 2));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("intArray数组下标越界异常。");
        } catch (ArithmeticException e) {
            System.out.println("除数为0异常。");
        }
        System.out.println("程序正常结束。");
    }

    /**
     * 带finally子句的异常处理程序。
     */
    static void f5(){
        int i = 0;
        String greetings[] = { " Hello world !", " Hello World !! ",
                " HELLO WORLD !!!" };
        while (i < 4) {
            try {
                // 特别注意循环控制变量i的设计，避免造成无限循环
                System.out.println(greetings[i++]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("数组下标越界异常");
            } finally {
                System.out.println("--------------------------");
            }
        }
    }

    public static void main(String[] args) {
        f1();
        f2();
        f3();
        f4();
        f5();
    }
}
