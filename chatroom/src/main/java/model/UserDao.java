package model;

import util.ChatroomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {


    //1.新增一个用户：注册
    public void add(User user){
        //1.获取数据库连接
        Connection c = DBUtil.getConnection();

        //2.拼装SQL语句
        String sql = "insert into user values(null,?,?,?,now())";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getName());
            //3.执行SQL语句
            //插入数据修改数据删除数据都为：executeUpdate
            //查找数据，就是executeQuery
            //返回结果为：影响到的行数
            int ret = ps.executeUpdate();
            if(ret != 1){
                throw new ChatroomException("插入用户异常");
            }
            System.out.println("插入成功");
            //4.释放连接
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("插入用户异常");
        }finally {
            DBUtil.close(c,ps,null);
        }

    }
    //2.按用户名查看信息：登录
    public User selectByName(String name){
        //1.获取到连接
        Connection c = DBUtil.getConnection();

        //2.拼装sql
        String sql = "select * from user where name = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //3.执行sql
            ps = c.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            //4.遍历结果集合.
            if(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setNickName(rs.getString("nickName"));
                user.setLastLogout(rs.getTimestamp("lastLogout"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("按用户名查找用户失败");
        }finally {
            //5.释放连接
            DBUtil.close(c,ps,rs);
        }

        return null;


    }
    //3.按用户ID查找用户信息

    public User selectById(int id){
        //1.获取到连接
        Connection c = DBUtil.getConnection();

        //2.拼装sql
        String sql = "select * from user where userId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //3.执行sql
            ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            //4.遍历结果集合.
            if(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setNickName(rs.getString("nickName"));
                user.setLastLogout(rs.getTimestamp("lastLogout"));
                return user;
            }
//            throw new ChatroomException("按用户ID查找用户异常")

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("按用户ID查找用户失败");
        }finally {
            //5.释放连接
            DBUtil.close(c,ps,rs);
        }

        return null;


    }

    //4.跟新用户的lastLogout时间
    public void updateLastLogout(int userId){
        //哪个用户下线了，就更新哪个
        //1.获取到连接
        Connection c = DBUtil.getConnection();
        //2.拼装sql
        String sql = "update user set lastLogout = now() where userId = ?";
        //3.执行sql
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            int ret= ps.executeUpdate();
            if(ret!=1){
                throw new ChatroomException("更新推出时间异常");
            }

            System.out.println("更新退出时间成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("更新退出时间异常");
        }finally{
            //4.释放连接
            DBUtil.close(c,ps,null);
        }



    }
}
