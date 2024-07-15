package javabfbcdys.chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-11 16:59
 * @description CopyOnWrite性能测试
 */
public class CopyOnWriteTest {
    public static void main(String[] args) {
        List<Long> copy = new CopyOnWriteArrayList<>();
        List<Long> arrayList = new ArrayList<>();

        long start = System.nanoTime();

        for (int i = 0; i < 20*10000; i++) {
            copy.add(System.nanoTime());
        }
        System.out.println("CopyOnWriteArrayList执行时间："+(System.nanoTime()-start)/1000/1000/1000);

        long start2 = System.nanoTime();
        for (int i = 0; i < 20*10000; i++) {
            arrayList.add(System.nanoTime());
        }
        System.out.println("ArrayList执行时间："+(System.nanoTime()-start2)/1000/1000/1000);
    }
}
