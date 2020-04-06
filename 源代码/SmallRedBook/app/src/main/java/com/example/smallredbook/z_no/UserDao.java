package com.example.smallredbook.z_no;

import android.util.Log;

//import com.example.smallredbook.utils.UserBean;

import com.example.smallredbook.model.UserBean;
import com.example.smallredbook.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static android.content.ContentValues.TAG;

public class UserDao {
    JdbcUtil jdbcUtil = JdbcUtil.getInstance();
    Connection conn = jdbcUtil.getConnection("dbtest","root","123456");

    public boolean register(String user_id,String password) {
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {
            String sql = "insert into user(user_id,password) values(?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,user_id);
                pre.setString(2,password);
                return pre.execute();
            } catch(SQLException e) {
                return false;
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean logining(String user_id,String password) {
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {
            String sql = "select * from user where user_id = ? and password = ?";
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setString(1,user_id);
                pres.setString(2,password);
                ResultSet res = pres.executeQuery();
                boolean t = res.next();
                return t;
            } catch (SQLException e) {
                return false;
            }
        }
    }

    public  void update(UserBean user) {

        String sql = "update user set nickName=?sex=? signature=? where user_id=? ";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, user.getNickName());
            pres.setString(2, user.getSex());
            pres.setString(3, user.getSignature());
            pres.setString(4, Integer.toString(user.getUser_id()));
             pres.executeUpdate();
            Log.i(TAG,"register:com is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  boolean changepwd(UserBean u){
        String sql = "update user set password=? where user_id=? ";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, u.getPassword());
            pres.setString(2, Integer.toString(u.getUser_id()));
            int count = pres.executeUpdate();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
        return false;
    }
}