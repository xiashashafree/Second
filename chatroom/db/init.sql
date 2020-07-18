create database chatroom character set utf8mb4;
use chatroom;
drop table if exists user;
create table user(
    userId int primary key auto_increment,
    name varchar(50) unique,
    password varchar(50),
    nickName varchar(50),
    lastLogout datetime
);

drop table if exists channel;
create table channel(
    channelId int primary key auto_increment,
    channelName varchar(50)
);

drop table if exists message;
create table message(
    messageId int primary key auto_increment,
    userId int,
    channelId int,
    context text,
    sendTime datetime
);
