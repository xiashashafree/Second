package model;

import util.ChatroomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                throw new ChatroomException("插入频道异常");
            }
            System.out.println("插入频道成功");
        } catch (SQLException e) {
           e.printStackTrace();
           throw new ChatroomException("插入频道异常");
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
                throw new ChatroomException("删除频道异常");
            }
            System.out.println("删除频道成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("删除频道失败");
        }finally {
            DBUtil.close(c,ps,null);
        }


    }
    //3.查看频道列表
    public List<Channel> select(){
        Connection c = DBUtil.getConnection();
        String sql = "select * from channel;";
        PreparedStatement ps =  null;
        ResultSet  rs = null;
        List<Channel> channels = new ArrayList<>();
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Channel channel = new Channel();
                channel.setChannelId(rs.getInt("channelId"));
                channel.setChannelName(rs.getString("channelName"));
                channels.add(channel);
            }
            return channels;
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            DBUtil.close(c,ps,null);
        }

        return null;
    }
    //4.指定用户关注

//    public static void main(String[] args) {
//        //创建一个ChannelDAO实例
//        ChannelDao channelDao = new ChannelDao();
//        Channel channel = new Channel();
////        channel.setChannelName("拢龙的家");
////        channelDao.add(channel);
//
//        List<Channel> channels = new ArrayList<>();
//        channels =  channelDao.select();
//        System.out.println(channels);
//
//        channelDao.delete(2);
//    }
}
