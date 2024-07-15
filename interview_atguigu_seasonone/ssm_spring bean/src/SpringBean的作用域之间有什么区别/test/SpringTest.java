package SpringBean的作用域之间有什么区别.test;

import SpringBean的作用域之间有什么区别.beans.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-28 10:15
 * @Describe
 */
public class SpringTest {
    //01.Spring Bean的作用域之间有什么区别

    //创建IOC容器对象
    ApplicationContext ioc = new ClassPathXmlApplicationContext("SpringBean的作用域之间有什么区别/beans.xml");

    @Test
    void testBook() {
        Book book = (Book) ioc.getBean("book");
        Book book2 = (Book) ioc.getBean("book");
        System.out.println(book==book2);
    }
}
