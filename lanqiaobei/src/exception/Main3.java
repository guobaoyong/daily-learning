package exception;

import java.io.IOException;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-01 21:30
 * @Describe 异常处理测试
 */
public class Main3 {

    static void pop() throws NegativeArraySizeException {
        // 定义方法并抛出NegativeArraySizeException异常
        int[] arr = new int[-3]; // 创建数组
    }

    //合法
    void method1() throws IOException{}

    //编译错误，必须捕获或声明抛出IOException
    void method2(){
        //method1();
    }

    //合法，声明抛出IOException
    void method3()throws IOException {
        method1();
    }

    //合法，声明抛出Exception，IOException是Exception的子类
    void method4()throws Exception {
        method1();
    }

    //合法，捕获IOException
    void method5(){
        try{
            method1();
        }catch(IOException e){ }
    }

    //编译错误，必须捕获或声明抛出Exception
    void method6(){
        try{
            method1();
        }catch(IOException e){
            //throw new Exception();
        }
    }

    //合法，声明抛出Exception
    void method7() throws Exception{
        try{
            method1();
        }catch(IOException e){throw new Exception();}
    }


    public static void main(String[] args) {
        try { // try语句处理异常信息
            pop(); // 调用pop()方法
        } catch (NegativeArraySizeException e) {
            System.out.println("pop()方法抛出的异常");// 输出异常信息
        }
    }
}
