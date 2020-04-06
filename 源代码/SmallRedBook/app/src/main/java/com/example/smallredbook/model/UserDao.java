package com.example.smallredbook.model;

import android.util.Log;

import com.example.smallredbook.utils.JdbcUtil;
import com.example.smallredbook.utils.Constants;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class UserDao {
    JdbcUtil jdbcUtil = JdbcUtil.getInstance();
    Connection conn = jdbcUtil.getConnection(Constants.SQL,Constants.SQL_user_name,Constants.SQL_password);
    //Connection conn = jdbcUtil.getConnection("dbtest","test","123456");
    public boolean register(String name,String password,String phone,String sex) {
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {
            String sql = "insert into user(user_name,password,telephone,sex) values(?,?,?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,name);
                pre.setString(2,password);
                pre.setString(3,phone);
                pre.setString(4,sex);
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

    public boolean logining(String name,String password) {
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {
            String sql = "select * from user where user_name = ? and password = ?";
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setString(1,name);
                pres.setString(2,password);
                ResultSet res = pres.executeQuery();
                boolean t = res.next();
                if(t){

//                    Log.i("获取的用户id",Integer.toString(Constants.user_id));
                   Constants.user_id = res.getInt("user_id");
                    Constants.user_address = res.getString("address");
                    Constants.user_name = res.getString("user_name");
                    Constants.user_head = res.getString("head_portrait");
                    Constants.phone= res.getString("telephone");
                    Constants.password = res.getString("password");
                    Constants.birthday = res.getString("birthday");
                    Constants.sex = res.getString("sex");
                    Constants.singnature = res.getString("describe");
//                    Constants.password = res.getString("password");
                   Log.i("设置后的用户id",Integer.toString(Constants.user_id));
                }

                return t;
            } catch (SQLException e) {
                return false;
            }
        }
    }

    public boolean published(String titl,String type,String text) {
        if(conn==null) {
            Log.i(TAG,"publish:com is null");
            return false;
        } else {
            Date curDate =  new Date(System.currentTimeMillis());
            String sql = "insert into note(user_id,note_time,title,type,text,note_context) values(?,?,?,?,?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1,Constants.user_id);
                pre.setDate(2,curDate);
                pre.setString(3,titl);
                pre.setString(4,type);
                pre.setString(5,text);
                pre.setString(6,Constants.mr_img);

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



    public boolean update_password(String new_password) {
        if(conn==null) {
            Log.i(TAG,"publish:com is null");
            return false;
        } else {
            String sql = "update  `user` SET `user`.password =?  where user_id =?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,new_password);
                pre.setInt(2,Constants.user_id);
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

    public boolean update_user_name(String new_user_name) {
        if(conn==null) {
            Log.i(TAG,"publish:com is null");
            return false;
        } else {
            String sql = "update  `user` SET `user`.user_name =?  where user_id =?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,new_user_name);
                pre.setInt(2,Constants.user_id);
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

    public boolean update_phone(String new_password) {
        if(conn==null) {
            Log.i(TAG,"publish:com is null");
            return false;
        } else {
            String sql = "update  `user` SET `user`.telephone =?  where user_id =?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,new_password);
                pre.setInt(2,Constants.user_id);
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


    public boolean update_address(String new_password) {
        if(conn==null) {
            Log.i(TAG,"publish:com is null");
            return false;
        } else {
            String sql = "update  `user` SET `user`.address =?  where user_id =?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,new_password);
                pre.setInt(2,Constants.user_id);
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



    public boolean update_describe(String new_password) {
        if(conn==null) {
            Log.i(TAG,"publish:com is null");
            return false;
        } else {
            String sql = "update  `user` SET `user`.describe =?  where user_id =?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,new_password);
                pre.setInt(2,Constants.user_id);
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



    public boolean update_sex(String new_password) {
        if(conn==null) {
            Log.i(TAG,"publish:com is null");
            return false;
        } else {
            String sql = "update  `user` SET `user`.sex =?  where user_id =?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,new_password);
                pre.setInt(2,Constants.user_id);
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


    public List<UserBean>  find_follow_users() {

        List<UserBean>  users= new ArrayList<>();

        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return null;
        } else {
            Log.i(TAG,"register:com is not null");
//            String sql = "select * from note ";
            String sql =
                    "select distinct concern_id as user_id,user_name,head_portrait as head from follow,`user` where concern_id = `user`.user_id and follow.user_id=?  ORDER BY user_name;";

            try {

                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setInt(1, Constants.user_id);
                ResultSet res = pres.executeQuery();
                while(res.next())
                {
                    Log.i(TAG,"获取数据+1");
                    UserBean user = new UserBean();
                    user.setUser_id(res.getInt("user_id"));
                    user.setUser_name( res.getString("user_name"));
                    user.setHead(res.getString("head"));
                    users.add(user);

                }
                return  users;
            } catch (SQLException e) {
                return null;
            }
        }

    }

    public boolean delete_follow_user_by_id(int user_id) {
        if(conn==null) {
            Log.i(TAG,"publish:com is null");
            return false;
        } else {
            String sql = "delete from follow where user_id =? and follow.concern_id =?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(2,user_id);
                pre.setInt(1,Constants.user_id);
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
}