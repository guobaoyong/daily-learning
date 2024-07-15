package serializable;

import java.io.Serializable;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-05 18:22
 * @Describe 序列化实体类
 */

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;
    private String sex;
    private transient String phone;

    public Person(String name, Integer age, String sex, String phone) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
