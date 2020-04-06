package com.example.smallredbook.model;

import java.util.Date;

public class UserBean {
    private int User_id;    //用户id
    private String user_name;
    private String address;
    private Date birthday;
    private String password;   //密码
    private String nickName;   //昵称
    private String sex;         //性别
    private String signature; //签名
    private String head;       //头像



    public int getUser_id() {

        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
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
    public String getSex() {

        return sex;
    }
    public void setSex(String sex) {

        this.sex = sex;
    }
    public String getSignature() {

        return signature;
    }
    public void setSignature(String signature) {

        this.signature = signature;
    }
    public String getHead() {

        return head;
    }
    public void setHead(String head) {

        this.head = head;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

