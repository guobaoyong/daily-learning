package javabfbcdys.chapter02;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-11 10:15
 * @description 原子引用解决ABA问题
 */
class User{
    private String username;
    private int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User z3 = new User("z3",22);
        User l4 = new User("l4",25);
        User w5 = new User("w5",22);

        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3,l4)+"\t"+atomicReference.get());
        System.out.println(atomicReference.compareAndSet(z3,l4)+"\t"+atomicReference.get());

    }
}
