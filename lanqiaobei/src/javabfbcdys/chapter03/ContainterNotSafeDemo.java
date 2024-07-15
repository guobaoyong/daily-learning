package javabfbcdys.chapter03;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-11 16:04
 * @description 集合类不安全的问题
 * list CopyOnWriteArrayList
 * set CopyOnWriteArraySet
 * map ConcurrentHashMap
 */
public class ContainterNotSafeDemo {
    public static void main(String[] args) {
        listNotSafe();
        setNotSafe();
        mapNotSafe();
    }

    public static void listNotSafe(){
        //数组实现，默认大小为10
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

        //java.util.ConcurrentModificationException异常
        /**
         * 1、故障现象
         *      java.util.ConcurrentModificationException
         * 2、导致原因
         *      并发争抢修改导致
         * 3、解决方案
         *      3.1 new Vector<>();
         *      3.2 Collections.synchronizedList(new ArrayList<>());
         *      3.2 new CopyOnWriteArrayList<>();
         * 4、优化建议
         *      写时复制，读写分离
         */
    }

    public static void setNotSafe(){
        //Collections.synchronizedSet(new HashSet<>());
        // new HashSet<>();
        //new CopyOnWriteArraySet<>();
        //CopyOnWriteArraySet底层使用CopyOnWriteArrayList实现
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }

        //HashSet底层是一个初始值为16，负载因子为0.75的HashMap
        //key 就是元素E，而Value则是一个PRESENT的Object常量
        new HashSet().add("a");
    }

    public static void mapNotSafe(){
        //new HashMap<>();
        //Collections.synchronizedMap(new HashMap<>());
        //new ConcurrentHashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
