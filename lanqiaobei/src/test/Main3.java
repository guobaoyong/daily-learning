package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-26 11:53
 * @description Collections.synchronizedList与CopyOnWriteArrayList比较
 * https://liuyanzhao.com/9732.html
 */
public class Main3 {

    /**
     * 单线程：性能较差
     */
    static void f1(){
        Long startTime = System.currentTimeMillis();
        //这是一个长度为1000的集合
        List<Long> sourceList = new ArrayList<>();
        for (long i = 0L; i < 1000L; i++) {
            sourceList.add(i);
        }
        System.out.println("原列表大小：" + sourceList.size());
        //对原列表进行处理
        List<Long> resultList = new ArrayList<>();
        for (Long x : sourceList) {
            //模拟耗时操作(x累加300万次)
            Long sum = 0L;
            for (long i = 0L; i < 3000000L; i++) {
                sum += x;
            }
            resultList.add(sum);
        }
        System.out.println("处理后的列表大小：" + resultList.size());
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 多线程版本，不安全的 ArrayList
     */
    static void f2(){
        Long startTime = System.currentTimeMillis();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        //这是一个长度为1000的集合
        List<Long> sourceList = new ArrayList<>();
        for (long i = 0L; i < 1000L; i++) {
            sourceList.add(i);
        }
        System.out.println("原列表大小：" + sourceList.size());
        List<Long> resultList = new ArrayList<>();
        for (Long x : sourceList) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //对原列表进行处理
                    //模拟耗时操作(x累加300万次)
                    Long sum = 0L;
                    for (long i = 0L; i < 3000000L; i++) {
                        sum += x;
                    }
                    resultList.add(sum);
                }
            });
        }
        fixedThreadPool.shutdown();//关闭线程池
        //此处不可以删除或注释，需要线程执行结束后再执行别的内容,即只有线程结束后才会继续向下执行
        while (!fixedThreadPool.isTerminated()) {
        }
        System.out.println("处理后的列表大小：" + resultList.size());
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 多线程版本，线程安全，CopyOnWriteArrayList()方式
     */
    static void f3(){
        Long startTime = System.currentTimeMillis();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        //这是一个长度为1000的集合
        List<Long> sourceList = new ArrayList<>();
        for (long i = 0L; i < 1000L; i++) {
            sourceList.add(i);
        }
        System.out.println("原列表大小：" + sourceList.size());
        List<Long> resultList = new CopyOnWriteArrayList<>();
        for (Long x : sourceList) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //对原列表进行处理
                    //模拟耗时操作(x累加300万次)
                    Long sum = 0L;
                    for (long i = 0L; i < 3000000L; i++) {
                        sum += x;
                    }
                    resultList.add(sum);
                }
            });
        }
        fixedThreadPool.shutdown();//关闭线程池
        //此处不可以删除或注释，需要线程执行结束后再执行别的内容,即只有线程结束后才会继续向下执行
        while (!fixedThreadPool.isTerminated()) {
        }
        System.out.println("处理后的列表大小：" + resultList.size());
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 多线程版本，线程安全，Collections.synchronizedList方式
     */
    static void f4(){
        Long startTime = System.currentTimeMillis();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        //这是一个长度为1000的集合
        List<Long> sourceList = new ArrayList<>();
        for (long i = 0L; i < 1000L; i++) {
            sourceList.add(i);
        }
        System.out.println("原列表大小：" + sourceList.size());
        List<Long> resultList = Collections.synchronizedList(new ArrayList<>());
        for (Long x : sourceList) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //对原列表进行处理
                    //模拟耗时操作(x累加300万次)
                    Long sum = 0L;
                    for (long i = 0L; i < 3000000L; i++) {
                        sum += x;
                    }
                    resultList.add(sum);
                }
            });
        }
        fixedThreadPool.shutdown();//关闭线程池
        //此处不可以删除或注释，需要线程执行结束后再执行别的内容,即只有线程结束后才会继续向下执行
        while (!fixedThreadPool.isTerminated()) {
        }
        System.out.println("处理后的列表大小：" + resultList.size());
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) {
        /*f1();*/
        /*f2();*/
        /*f3();*/
        f4();
    }
}
