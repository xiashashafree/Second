package model;
//一个User对象就用来表示一条数据的记录
//对象的属性基本和数据库的表结构一致

import java.sql.Timestamp;

public class User {
    private int userId;
    private String name;
    private String password;
    private String nickName;
    private Timestamp lastLogout;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Timestamp getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(Timestamp lastLogout) {
        this.lastLogout = lastLogout;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", lastLogout=" + lastLogout +
                '}';
    }
}
