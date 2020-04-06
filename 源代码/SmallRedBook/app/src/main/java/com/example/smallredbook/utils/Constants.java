package com.example.smallredbook.utils;

public class Constants {
//用于记录登录用户的信息
    public  static  int user_id = 0;
    public  static  String user_address = "";
    public static String user_name ="";
    public static String user_head="";
    public static String password = "";
    public static String phone;
    public static String birthday;
    public static String sex;
    public static String singnature;
    public static String mr_img="note.jpg";




//用于配置数据库和服务器的相关信息，运行时请根据自己的数据库和本地服务器进行相应的更改，否则无法连接数据库或服务器
    public  static  String HOMR_URL="jdbc:mysql://192.168.31.226:3306/";                        //数据库地址
    public static String Note_URL = "http://192.168.31.226:8080/SmallRedBook/note_context/";    //存放笔记中图片或视频的本地服务器地址
    public static String User_URL = "http://192.168.31.226:8080/SmallRedBook/user_head_img/";    //存放用户头像图片的本地服务器地址
    public static String  SQL  = "srb";  //数据库名
    public static  String SQL_user_name = "srb_user";    //数据库用户名
    public static String  SQL_password = "123456";   //数据库用户密码
//


}
