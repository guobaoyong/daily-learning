package org.wlgzs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-11-07 21:20
 * @Describe 数据库连接工具
 */
public class DbUtil {

    public Connection getCon() throws ClassNotFoundException, SQLException {
            Class.forName(PropertiesUtil.getValue("jdbcName"));
            Connection connection = DriverManager.getConnection(PropertiesUtil.getValue("dbUrl"), PropertiesUtil.getValue("dbUserName"),  PropertiesUtil.getValue("dbPassword"));
            return  connection;
    }

    public void closeCon(Connection connection ){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil  = new DbUtil();
        try {
            Connection con = dbUtil.getCon();
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

}
