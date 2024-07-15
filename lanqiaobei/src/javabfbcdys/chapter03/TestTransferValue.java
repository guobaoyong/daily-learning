package javabfbcdys.chapter03;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-11 17:11
 * @description
 * 变量作用域小测试
 */
public class TestTransferValue {

    public void changeValue1(int age){
        age = 30;
    }

    public void changeValue2(Person person){
        person.setPersonName("XXX");
    }

    public void changeValue3(String str){
        str = "XXX";
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age----------"+age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName-----------"+person.getPersonName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("str----------"+str);
    }
}

class Person{
    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}