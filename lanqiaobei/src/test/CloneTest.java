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
        // ��¡��һ������
        Student newStudent = student.clone();

        // �޸�ԭ�ж��������
        student.setName("HanMeimei");
        student.getAchievement().setChinese(90);
        student.getAchievement().setEnglish(90);
        student.getAchievement().setMath(90);

        System.out.println(newStudent);
        System.out.println(student);
    }
}
