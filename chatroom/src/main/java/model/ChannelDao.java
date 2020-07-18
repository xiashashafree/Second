package model;

import util.ChatroomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChannelDao {

    //1.新增频道
    public void add(Channel channel){
        Connection c = DBUtil.getConnection();
        String sql = "insert into channel values(null,?);";
        PreparedStatement ps =  null;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, channel.getChannelName());
            int ret = ps.executeUpdate();
            if(ret!=1){
                throw new ClassCastException("插入频道异常");
            }
            System.out.println("插入频道成功");
        } catch (SQLException e) {
           e.printStackTrace();
           throw new ChatroomException("插入频道成功");
        }finally {
            DBUtil.close(c,ps,null);
        }


    }
    //2.删除频道
    public void delete(int channelId){
        Connection c = DBUtil.getConnection();
        String sql = "delete  from channel where channelId = ?;";
        PreparedStatement ps =  null;
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, channelId);
            int ret = ps.executeUpdate();
            if(ret!=1){
                throw new ClassCastException("删除频道异常");
            }
            System.out.println("删除频道成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("删除频道成功");
        }finally {
            DBUtil.close(c,ps,null);
        }


    }
    //3.查看频道
    public void select(Channel channel){
        Connection c = DBUtil.getConnection();
        String sql = "insert into channel values(null,?);";
        PreparedStatement ps =  null;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, channel.getChannelName());
            int ret = ps.executeUpdate();
            if(ret!=1){
                throw new ClassCastException("插入频道异常");
            }
            System.out.println("插入频道成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("插入频道成功");
        }finally {
            DBUtil.close(c,ps,null);
        }


    }
    //4.指定用户关注

}
