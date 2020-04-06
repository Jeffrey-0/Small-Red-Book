package com.example.smallredbook.z_no;

public class user {
    private String userName;   //用户名
    private String nickName;   //昵称
    private String sex;         //性别
    private String signature; //签名
    private String head;       //头像
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName ){
        this.userName = userName;
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
}

