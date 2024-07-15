package instanceofTest;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-21 20:02
 * @Describe
 */

interface AA{
    void say();
}

class BB implements AA{
    public void say()
    {
        System.out.println("B实现的say()方法");
    }
}

class CC implements AA{
    public void say()
    {
        System.out.println("C实现的say()方法");
    }
}

public class Main2 {
    public static void main(String[] args) {
        AA a= new BB();  //接口不能new
        System.out.println(a instanceof BB);   //true;发生了A a= new B();
        System.out.println(a instanceof CC);   //false;没有发生A a = new C();
    }
}
