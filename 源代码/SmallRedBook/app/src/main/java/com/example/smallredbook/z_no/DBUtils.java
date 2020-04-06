package com.example.smallredbook.z_no;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.smallredbook.model.UserBean;


public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;

    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean login_ok(String username, String password){
        SQLiteDatabase sdb=helper.getReadableDatabase();
        String sql="select * from userinfo where username=? and password=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{username, password});
            if (cursor.moveToFirst() == true) {
                cursor.close();
                return true;
            }
        return false;
    }
    public boolean register(UserBean user){
        SQLiteDatabase sdb=helper.getReadableDatabase();
        String sql="insert into userinfo(userName,pwd) values(?,?)";
        Object obj[]={user.getUser_id(),user.getPassword()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public void saveUserInfo(UserBean bean) {
        ContentValues cv = new ContentValues();
        cv.put("userName", bean.getUser_id());
        cv.put("pwd", bean.getPassword());
        cv.put("nickName", bean.getNickName());
        cv.put("sex", bean.getSex());
        cv.put("signature", bean.getSignature());

        db.insert(SQLiteHelper.U_USERINFO, null, cv);
    }
    /**
     * 获取个人资料信息
     */
    public UserBean getUserInfo(String userName) {
//        String sql = "SELECT * FROM userinfo  WHERE userName=?";
//        Cursor cursor = db.rawQuery(sql, new String[]{userName});
//        UserBean bean = null;
//        while (cursor.moveToNext()) {
//            bean = new UserBean();
//            bean.setUser_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("userName"))));
//            bean.setPassword(cursor.getString(cursor.getColumnIndex("pwd")));
//            bean.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));
//            bean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
//            bean.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
//            bean.setHead(cursor.getString(cursor.getColumnIndex("head")));
//
//        }
//        cursor.close();
        return null;
    }
    /**
     * 修改个人资料
     */
    public void updateUserInfo(String key, String value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQLiteHelper.U_USERINFO, cv, "userName=?",new String[]{userName});
    }


}
