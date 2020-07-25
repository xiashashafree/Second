package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

//用来实现websocket接口
//userId是一个变量，客户端连接的时候，具体提交的userId看连接的对象
@ServerEndpoint(value = "/message/{userId}")
public class MessageAPI {
    private Gson gson = new GsonBuilder().create();
    //通过用户连接的url中获取到的
    private int userId;
    @OnOpen
    public  void onOpen(@PathParam("userId") String userIdStr, Session session) throws IOException {
        //1.获取userId
        System.out.println("连接建立"+userIdStr);
        this.userId = Integer.parseInt(userIdStr);
        //2.把该用户加入到在线用户列表中
        MessageCenter.getInstance().addOnlineUser(userId,session);
        //3.拉取历史消息，并直接转发给该用户
        UserDao userDao = new UserDao();
        User user = userDao.selectById(userId);
        MessageDao messageDao = new MessageDao();
        List<Message> messages= messageDao.select(user.getLastLogout(),new Timestamp(System.currentTimeMillis()));
        for(Message message:messages){
            String jsonString = gson.toJson(message);
            session.getBasicRemote().sendText(jsonString);
        }

    }
    @OnClose
    public void onClose(){
        //把用户从在线列表中删掉
        System.out.println("连接断开");
        MessageCenter.getInstance().delOnlineUser(userId);
        //更新用户下线时间
        UserDao userDao = new UserDao();
        userDao.updateLastLogout(userId);
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("连接出现错误"+userId);
        error.printStackTrace();
        MessageCenter.getInstance().delOnlineUser(userId);
        //更新用户下线时间
        UserDao userDao = new UserDao();
        userDao.updateLastLogout(userId);
    }

    @OnMessage
    public void onMessage(String request,Session session) throws InterruptedException {
        System.out.println("收到消息！"+userId+"："+request);
        //1.解析message格式。收到的request对象时一个json格式的字符串
        Message message = gson.fromJson(request,Message.class);
        //2.把消息的发送时间填充到message中
        message.setSendTime(new Timestamp(System.currentTimeMillis()));
        //3.把消息加入到消息中心的队列中
        MessageCenter.getInstance().addMessage(message);
        MessageDao messageDao = new MessageDao();
        //4.将消息写入数据库
        messageDao.add(message);
    }

}
