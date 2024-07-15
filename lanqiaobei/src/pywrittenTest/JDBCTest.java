package pywrittenTest;

import java.sql.*;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-21 20:19
 * @Describe JDBCTest，PreparedStatement与Statement区别
 */
public class JDBCTest {

    class Student{
        private String name;
        private Integer age;
        private String birthday;

        public Student(String name, Integer age, String birthday) {
            this.name = name;
            this.age = age;
            this.birthday = birthday;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }

    public void t() throws ClassNotFoundException, SQLException {
        //1 加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2 获取数据库连接
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root" ;
        String password = "000000" ;
        Connection conn = DriverManager.getConnection(url, user, password);
        Student student = new Student("张三",20,"19860101");
        //3 创建一个Statement
        String sql = "insert into students (name,age,birthday) values(?,?,?)" ;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getBirthday());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conn.close();
        }
    }
}
