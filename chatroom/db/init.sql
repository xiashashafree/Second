create database chatroom;
use chatroom;
create table user(
    userId int primary key auto_increment,
    name varchar(50) unique,
    password varchar(50),
    nickName varchar(50),
    lastLogout datetime

)