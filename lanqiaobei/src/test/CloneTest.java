package test;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-06-30 14:47
 * @description
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Achievement achievement = new Achievement(100,100,100);
        Student student = new Student("LiLei",achievement);
        // 克隆出一个对象
        Student newStudent = student.clone();

        // 修改原有对象的属性
        student.setName("HanMeimei");
        student.getAchievement().setChinese(90);
        student.getAchievement().setEnglish(90);
        student.getAchievement().setMath(90);

        System.out.println(newStudent);
        System.out.println(student);
    }
}
