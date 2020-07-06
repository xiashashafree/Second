package free.util;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import free.excpetion.SystemException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/book";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static volatile DataSource DATA_SOURCE;
    private static DataSource getDataSource(){
        if(DATA_SOURCE == null){
            synchronized (DBUtil.class){
                if(DATA_SOURCE == null){
                    DATA_SOURCE = new MysqlDataSource();
                    ((MysqlDataSource) DATA_SOURCE ).setUrl(URL);
                    ((MysqlDataSource) DATA_SOURCE ).setUser(USERNAME);
                    ((MysqlDataSource) DATA_SOURCE ).setPassword(PASSWORD);
                }
            }
        }
        return DATA_SOURCE;
    }
    public static Connection getConnection(){

        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
           throw new SystemException("00001","获取数据库异常",e);
        }
    }
    public static void close(Connection c,Statement s){
        try {

            if(s != null){
                s.close();
            }
            if(c!=null){
                c.close();
            }
        } catch (SQLException e) {
            throw new SystemException("000002","数据库资源释放异常",e);
        }
    }
    public static void close(Connection c, Statement s, ResultSet r){
        try {
            if(r != null){
                r.close();
            }
            if(s != null){
                s.close();
            }
            if(c!=null){
                c.close();
            }
        } catch (SQLException e) {
            throw new SystemException("000002","数据库资源释放异常");
        }
    }
}
