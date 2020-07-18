package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//管理数据库连接，本质是实现了DataSource类的单例版本
//对一个应用程序来说，DataBase只需要有一个实例就可以了
//多线程安全版的单例模式：
//1.合适的位置加锁
//2.双重if判定
//3.volatile
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/chatroom?character=utf-8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static  DataSource dataSource = null;

    public static DataSource getDataSource(){
        if(dataSource == null){
            synchronized (DBUtil.class){
                //获取单例的DataSource
                if(dataSource == null){
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL(URL);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection(){
        try{
          return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection c, PreparedStatement s, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if(s!=null){
                s.close();
            }
            if(c!=null){
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
