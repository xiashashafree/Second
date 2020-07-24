package model;


import util.ChatroomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//将消息保存，借此实现历史消息功能（用户上次下线到这次上线之间的消息）
public class MessageDao {
    //1.新增消息
    public void add(Message message){
        Connection c = DBUtil.getConnection();
        String sql = "insert into message values(null,?,?,?,?);";
        PreparedStatement ps = null;
        try{
            ps = c.prepareStatement(sql);
            ps.setInt(1,message.getUserId());
            ps.setInt(2,message.getChannelId());
            ps.setString(3,message.getContent());
            ps.setTimestamp(4,message.getSendTime());
            int ret = ps.executeUpdate();
            if(ret != 1){
                throw new ChatroomException("插入消息失败");
            }
            System.out.println("插入消息成功");
        }catch (SQLException e){
            e.printStackTrace();
            new ChatroomException("插入消息失败");
        }finally {
            DBUtil.close(c,ps,null);
        }

    }
    //2.按时间段查询消息
    public List<Message> select(Timestamp from, Timestamp to){

        Connection c = DBUtil.getConnection();
        String sql = "select * from message where sendTime >= ? and sendTime<=?;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<>();
        try {
            ps = c.prepareStatement(sql);
            ps.setTimestamp(1,from);
            ps.setTimestamp(2,to);
            System.out.println("selectByTime: "+ps);
            rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message();
                message.setChannelId(rs.getInt("channelId"));
                message.setMessageId(rs.getInt("messageId"));
                message.setContent(rs.getString("content"));
                message.setUserId(rs.getInt("userId"));
                message.setSendTime(rs.getTimestamp("sendTime"));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(c,ps,rs);
        }
        return null;

    }

    public static void main(String[] args) {
        MessageDao messageDao = new MessageDao();
        Message message = new Message();
        message.setUserId(1);
        message.setChannelId(2);
        message.setContent("江月晃重山");
        message.setSendTime(new Timestamp(System.currentTimeMillis()));
        messageDao.add(message);
        message.setUserId(2);
        message.setChannelId(1);
        message.setContent("（づ￣3￣）づ╭❤～");
        message.setSendTime(new Timestamp(System.currentTimeMillis()));
        messageDao.add(message);
        List<Message> messages = messageDao.select(new Timestamp(1595600000000L),
                new Timestamp(1595680888888L));
        System.out.println(messages);
    }

}
