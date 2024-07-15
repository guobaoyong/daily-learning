package test;

import java.util.Comparator;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-25 17:20
 * @Description Comparator测试类
 */
public class ComparatorTest implements Comparator<ComparableTest> {


    @Override
    public int compare(ComparableTest o1, ComparableTest o2) {
        if (o1.getString().compareTo(o2.getString()) > 0)
            return 1;
        else if (o1.getString().compareTo(o2.getString()) == 0)
            return 0;
        else
            return -1;
    }

    public static void main(String[] args) {
        ComparableTest a = new ComparableTest("c");
        ComparableTest b = new ComparableTest("c");
        ComparableTest c = new ComparableTest("b");
        ComparableTest d = new ComparableTest("d");
        ComparatorTest dc = new ComparatorTest();
        System.out.println(dc.compare(a,b));
        System.out.println(dc.compare(a,c));
        System.out.println(dc.compare(a,d));
    }
}
