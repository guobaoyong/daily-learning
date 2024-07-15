package test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-31 14:14
 * @description Steam流测试
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(20,"张三"));
        users.add(new User(22, "李四"));
        users.add(new User(10, "王五"));

        //集合中内部迭代
        users.forEach(p ->{
            System.out.println(p);
        });

        Stream<User> stream = users.stream();
        //过滤年龄大于20的
        stream.filter(p -> p.getAge() > 20);

        //所有的年龄大于20岁的User对象，转换为字符串50对象。现在流中只有字符串对象了。
        stream.filter((User user) ->  user.getAge() > 20).map((User user) -> {return "50";});

        //返回流中元素的个数。
        long count = stream.filter((User user) ->  user.getAge() >= 20).map((User user) -> {return "50";})
                .count();
        System.out.println(count);
    }
}

class User{
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
