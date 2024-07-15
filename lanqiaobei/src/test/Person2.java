package test;

import java.util.Optional;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-31 13:57
 * @description Optional≤‚ ‘
 */
public class Person2 {
    private String name;

    public Person2() {
    }

    public Person2(String name) {
        this.name = name;
    }

    /*public String getName() {
        if (name == null) {
            return "";
        } else {
            return name;
        }
    }*/

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public static void main(String[] args) {
        Person2 person = new Person2();

        /*String name = person.getName();*/

        /*if (name != null && name.equals("Jackson")) {
            System.out.println("My name is Jackson!");
        }*/

        Optional<String> optionalName = person.getName();
        if (optionalName.isPresent()) {
            String name = optionalName.get();
            System.out.println("My name is :" + name);
        } else {
            System.out.println("Who am I?");
        }
    }
}
