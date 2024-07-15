package test;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-06-30 14:45
 * @description
 */
class Student implements Cloneable{
    private String name;
    private Achievement achievement; //≥…º®

    public Student(String name, Achievement achievement) {
        this.name = name;
        this.achievement = achievement;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", achievement=" + achievement +
                '}';
    }

    @Override
    protected Student clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        Achievement achievement = student.getAchievement().clone();
        student.setAchievement(achievement);
        return student;
    }
}

/**
 * ≥…º®¿‡
 */
class Achievement implements Cloneable{
    private float Chinese;
    private float math;
    private float English;

    public Achievement(float chinese, float math, float english) {
        Chinese = chinese;
        this.math = math;
        English = english;
    }

    public void setChinese(float chinese) {
        Chinese = chinese;
    }

    public void setMath(float math) {
        this.math = math;
    }

    public void setEnglish(float english) {
        English = english;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "Chinese=" + Chinese +
                ", math=" + math +
                ", English=" + English +
                '}';
    }

    @Override
    protected Achievement clone() throws CloneNotSupportedException {
        return (Achievement) super.clone();
    }
}
