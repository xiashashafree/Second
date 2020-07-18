package model;

import java.sql.Timestamp;

public class Message {

    private int messageId;
    private int userId;
    private int channelId;
    private String context;
    private Timestamp sendTime;


    private String nickName;//message表中没有，通过UserId在user表中找，显示在页面

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                ", channelId=" + channelId +
                ", context='" + context + '\'' +
                ", sendTime=" + sendTime +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
