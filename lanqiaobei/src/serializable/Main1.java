package serializable;

import java.io.*;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-05 18:24
 * @Describe 序列化测试类
 */
public class Main1 {

    /**
     * 对象的序列化操作
     * @throws IOException
     */
    static void SerializePerson() throws IOException {
        Person person = new Person("zsh",21,"男","123456789  ");
        //ObjectOutputStream 对象输出流，将Person对象储存在指定路径下的data.txt文件中，完成对Person对象的序列化
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\serializable\\data.txt")));
        oo.writeObject(person);
        System.out.println("对象序列化成功");
        oo.close();
    }

    /**
     * 对象的反序列化操作
     * @return Person对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static Person DeserializePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\serializable\\data.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("对象反序列化成功");
        ois.close();
        return person;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializePerson();
        System.out.println(DeserializePerson());
    }
}
