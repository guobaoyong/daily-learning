package javabfbcdys.chapter07;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-23 8:21
 * @description
 */
public class AtomicReferenceTest {

    public static AtomicReference<User> atomicReference = new AtomicReference<User>();

    public static void main(String[] args) {
        User user = new User("zsh",22);
        atomicReference.set(user);
        User updateUser = new User("zzz",18);
        atomicReference.compareAndSet(user,updateUser);
        System.out.println(atomicReference.get().toString());
    }

    static class User{
        private String name;
        private int old;

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

