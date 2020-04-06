package com.example.smallredbook.z_no;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    public static String DB_NAME = "newsdemo.db";
    public static final String U_USERINFO = "userinfo"; //用户信息

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 创建用户信息表
         */
        db.execSQL("CREATE TABLE  userinfo( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR, "  //用户名
                + "pwd VARCHAR, "  //密码
                + "nickName VARCHAR, "  //昵称
                + "sex VARCHAR, "        //性别
                + "signature VARCHAR,"  //签名
                + "head VARCHAR "        //头像
                + ")");
        /**
         * 创建十二星座信息表
         */
         /* db.execSQL("CREATE TABLE  IF NOT EXISTS " + CONSTELLATION + "( "
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "c_id INT, "               //星座id
                + "name VARCHAR, "          //星座名称
                + "head VARCHAR, "          //头像
                + "img VARCHAR,"            //图标
                + "icon VARCHAR,"           //白色图标
                +"date VARCHAR,"            //日期
                +"info VARCHAR,"            //星座信息
                +"whole INT,"               //整体运势
                +"love INT,"                //爱情运势
                +"career INT,"             //事业学业
                +"money INT,"              //财富运势
                +"whole_info VARCHAR,"   //整体运势信息
                +"love_info VARCHAR,"    //爱情运势信息
                +"career_info VARCHAR," //事业学业信息
                +"money_info VARCHAR,"  //财富运势信息
                +"health_info VARCHAR"  //健康运势信息
                + ")");
        /**
         * 创建收藏表
         */
       /* db.execSQL("CREATE TABLE  IF NOT EXISTS " + COLLECTION_NEWS_INFO + "( "
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "id INTEGER, "         //新闻id
                + "type INTEGER, "      //新闻类型
                + "userName VARCHAR," //用户名
                + "newsName VARCHAR, "      //新闻名称
                + "newsTypeName VARCHAR," //新闻类型名称
                + "img1 VARCHAR, "        //图片1
                + "img2 VARCHAR, "       //图片2
                + "img3 VARCHAR, "      //图片3
                + "newsUrl VARCHAR "  //新闻链接地址
                + ")");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + U_USERINFO);
      //  db.execSQL("DROP TABLE IF EXISTS " + CONSTELLATION);
       // db.execSQL("DROP TABLE IF EXISTS " + COLLECTION_NEWS_INFO);
        onCreate(db);
    }
}


