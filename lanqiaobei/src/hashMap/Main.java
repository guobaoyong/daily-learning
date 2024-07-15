package hashMap;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-28 21:00
 * @Describe
 * 重写equals方法而不重写hashcode方法发生的问题
 */
public class Main {
    public static void main(String[] args) {
        HashMap<Person,String> map = new HashMap<>();
        Person p1 = new Person(1, "张三");
        map.put(p1,"1号");
        System.out.println(map.get(new Person(1, "张三")));
    }
}

class Person{
    private Integer id;
    private String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name);
    }

    /*@Override
    public int hashCode() {
        return Objects.hash(id, name);
    }*/
}
