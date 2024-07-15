package javabfbcdys.chapter07;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-23 8:41
 * @description
 */
public class AtomicIntegerFieldUpdaterTest {
    //创建原子更新器，并设置需要更新的对象类和对象的属性
    private static AtomicIntegerFieldUpdater<User> a =
            AtomicIntegerFieldUpdater.newUpdater(User.class,"old");

    public static void main(String[] args) {
        User user = new User("zsh",10);
        System.out.println(a.getAndIncrement(user));
        System.out.println(a.get(user));
    }

    public static  class User{
        private String name;
        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", old=" + old +
                    '}';
        }
    }
}
