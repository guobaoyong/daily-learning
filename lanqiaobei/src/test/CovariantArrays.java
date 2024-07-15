package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-25 9:51
 * @Description extends和super泛型限定符（上界不取下界不存）
 */
class Fruit{}
class Apple extends Fruit{}
class Jonathan extends Apple{}
class Orange extends Fruit{}

public class CovariantArrays {
    public static void main(String[] args) {
        //上界
        List<? extends Fruit> flistTop = new ArrayList<Apple>();
        flistTop.add(null);
        //flistTop.add(new Fruit());
        Fruit fruit = flistTop.get(0);
        System.out.println(fruit);

        //下界
        List<? super Apple> firstBottem = new ArrayList<Apple>();
        firstBottem.add(new Apple());
        firstBottem.add(new Jonathan());
        //Apple object = firstBottem.get(0);
        Object object = firstBottem.get(0);
        System.out.println(object);

    }
}
