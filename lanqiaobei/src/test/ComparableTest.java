package test;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-25 17:13
 * @Description Comparable测试
 */
public class ComparableTest implements Comparable<ComparableTest>{

    private String string;

    public ComparableTest(String string) {
        this.string = string;
    }

    @Override
    public int compareTo(ComparableTest o) {
        if (this.string.compareTo(o.string) > 0)
            return 1;
        else if (this.string.compareTo(o.string) == 0)
            return 0;
        else
            return -1;
    }

    public String getString() {
        return string;
    }

    public static void main(String[] args) {
        ComparableTest a = new ComparableTest("c");
        ComparableTest b = new ComparableTest("c");
        ComparableTest c = new ComparableTest("b");
        ComparableTest d = new ComparableTest("d");
        System.out.println(a.compareTo(b));
        System.out.println(a.compareTo(c));
        System.out.println(a.compareTo(d));
    }
}
