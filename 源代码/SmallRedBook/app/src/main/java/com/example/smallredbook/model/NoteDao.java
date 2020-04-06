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

public class NoteDao {
    JdbcUtil jdbcUtil = JdbcUtil.getInstance();
    Connection conn = jdbcUtil.getConnection(Constants.SQL,Constants.SQL_user_name,Constants.SQL_password);
    //Connection conn = jdbcUtil.getConnection("dbtest","test","123456");

    public List<Note> find_all_note(){                          //查找所有笔记
        List<Note> notes = new ArrayList<>();

        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return null;
        } else {
            Log.i(TAG,"register:com is not null");
//            String sql = "select * from note ";
            String sql =
                    "select note.*,user_name ,head_portrait ," +
                            "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                            "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                            "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                            " from note_view as note,`user`" +
                            "WHERE note.user_id = `user`.user_id  ORDER BY note_time DESC;";

            try {

                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setInt(1, Constants.user_id);
                pres.setInt(2, Constants.user_id);
                pres.setInt(3, Constants.user_id);
                ResultSet res = pres.executeQuery();
                while(res.next())
                {
                    Log.i(TAG,"获取数据+1");
                    Note note = new Note();
                    note.setNote_id(res.getInt("note_id"));
                    note.setUser_id(res.getInt("user_id"));
                    note.setNote_time(res.getDate("note_time"));
                    note.setTitle(res.getString("title"));
                    note.setText(res.getString("text"));
                    note.setType(res.getString("type"));
                    note.setNote_content(res.getString("note_context"));
                    note.setNum_love(res.getInt("num_love"));
                    note.setNum_collect(res.getInt("num_collect"));
                    note.setNum_comment(res.getInt("num_comment"));

                    note.setHead_portrait(res.getString("head_portrait"));
                    note.setUser_name(res.getString("user_name"));
                    note.setIf_follow(res.getInt("if_follow"));
                    note.setIf_collect(res.getInt("if_collect"));
                    note.setIf_love(res.getInt("if_love"));


                    notes.add(note);

                }
                return  notes;
            } catch (SQLException e) {
                return null;
            }
        }
    }

    public Note find_note_by_id(Integer note_id) {                          //通过id查找某个笔记

        if (conn == null) {
            Log.i(TAG, "register:com is null");
            return null;
        } else {
//            String sql = "select * from note where note_id =? ";

            String sql =
                    "select note.*,user_name ,head_portrait ," +
                            "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                            "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                            "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                            " from note_view as note,`user`" +
                            "WHERE note.user_id = `user`.user_id and note.note_id = ?  ";


            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, Constants.user_id);
                pre.setInt(2, Constants.user_id);
                pre.setInt(3, Constants.user_id);
                pre.setInt(4, note_id);
                ResultSet res = pre.executeQuery();
                while (res.next()) {
                    Note note = new Note();
                    note.setNote_id(res.getInt("note_id"));
                    note.setUser_id(res.getInt("user_id"));
                    note.setNote_time(res.getDate("note_time"));
                    note.setTitle(res.getString("title"));
                    note.setText(res.getString("text"));
                    note.setType(res.getString("type"));
                    note.setNote_content(res.getString("note_context"));
                    note.setNum_love(res.getInt("num_love"));
                    note.setNum_collect(res.getInt("num_collect"));
                    note.setNum_comment(res.getInt("num_comment"));

                    note.setHead_portrait(res.getString("head_portrait"));
                    note.setUser_name(res.getString("user_name"));
                    note.setIf_follow(res.getInt("if_follow"));
                    note.setIf_collect(res.getInt("if_collect"));
                    note.setIf_love(res.getInt("if_love"));

                    return note;
                }

            } catch (SQLException e) {
                return null;
            }
        }
        return  null;
    }


    public List<Note> find_note_by_type(String type){                          //通过类型查找某种笔记
        List<Note> notes = new ArrayList<>();

        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return null;
        } else {
            String sql =
                    "select note.*,user_name ,head_portrait ," +
                            "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                            "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                            "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                            " from note_view as note,`user`" +
                            "WHERE note.user_id = `user`.user_id and note.type = ?  ORDER BY note_time DESC;";
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setInt(1, Constants.user_id);
                pres.setInt(2, Constants.user_id);
                pres.setInt(3, Constants.user_id);
                pres.setString(4,type);
                ResultSet res = pres.executeQuery();
                while(res.next())
                {
                    Note note = new Note();
                    note.setNote_id(res.getInt("note_id"));
                    note.setUser_id(res.getInt("user_id"));
                    note.setNote_time(res.getDate("note_time"));
                    note.setTitle(res.getString("title"));
                    note.setText(res.getString("text"));
                    note.setType(res.getString("type"));
                    note.setNote_content(res.getString("note_context"));
                    note.setNum_love(res.getInt("num_love"));
                    note.setNum_collect(res.getInt("num_collect"));
                    note.setNum_comment(res.getInt("num_comment"));
                    note.setHead_portrait(res.getString("head_portrait"));


                    note.setUser_name(res.getString("user_name"));
                    note.setIf_follow(res.getInt("if_follow"));
                    note.setIf_collect(res.getInt("if_collect"));
                    note.setIf_love(res.getInt("if_love"));

                    notes.add(note);
                }
                return  notes;
            } catch (SQLException e) {
                return null;
            }
        }
    }
    public List<Note> search_note_by_text(String text){                          //通过搜索查找某种笔记
        List<Note> notes = new ArrayList<>();

        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return null;
        } else {
            String sql =
                    "select note.*,user_name ,head_portrait ," +
                            "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                            "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                            "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                            " from note_view as note,`user`" +
                            "WHERE note.user_id = `user`.user_id and (type like ? or title LIKE ?)   ORDER BY note_time DESC;";

            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setInt(1, Constants.user_id);
                pres.setInt(2, Constants.user_id);
                pres.setInt(3, Constants.user_id);
                pres.setString(4,'%'+text+'%');
                pres.setString(5,'%'+text+'%');
                ResultSet res = pres.executeQuery();
                while(res.next())
                {
                    Note note = new Note();
                    note.setNote_id(res.getInt("note_id"));
                    note.setUser_id(res.getInt("user_id"));
                    note.setNote_time(res.getDate("note_time"));
                    note.setTitle(res.getString("title"));
                    note.setText(res.getString("text"));
                    note.setType(res.getString("type"));
                    note.setNote_content(res.getString("note_context"));
                    note.setNum_love(res.getInt("num_love"));
                    note.setNum_collect(res.getInt("num_collect"));
                    note.setNum_comment(res.getInt("num_comment"));

                    note.setHead_portrait(res.getString("head_portrait"));
                    note.setUser_name(res.getString("user_name"));
                    note.setIf_follow(res.getInt("if_follow"));
                    note.setIf_collect(res.getInt("if_collect"));
                    note.setIf_love(res.getInt("if_love"));

                    notes.add(note);
                }
                return  notes;
            } catch (SQLException e) {
                return null;
            }
        }
    }


    public List<Note> find_my_note_by_type(int user_id,int num){         //查找该用户的某种笔记,第二个参数num确定类型（0：自己的笔记，1：收藏的笔记，2，点赞过的笔记，3.关注用户的所有笔记,4.附近的笔记）
        List<Note> notes = new ArrayList<>();
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return null;
        } else {
            String sql;
            switch(num){
                case 0:
                     sql =
                            "select note.*,user_name ,head_portrait ," +
                                    "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                                    "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                                    "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                                    " from note_view as note,`user`" +
                                    "WHERE note.user_id = `user`.user_id  and note.user_id = ?  ORDER BY note_time DESC;";

//                    sql = "select note.*,user_name ,head_portrait ," +
//                            "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
//                            "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
//                            "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
//                            " from note_view as note,`user` " +
//                            "WHERE note.user_id = `user`.user_id;";
                    break;


//                    sql = "select * from note where user_id = ? ;";break;
                case 1:
                   sql = "select note.*,user_name ,head_portrait ," +
                                    "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                                    "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                                    "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                                    " from note_view as note,`user`" +
                                    "WHERE note.user_id = `user`.user_id and note.note_id  in (select collect_note from collect where user_id = ?) ORDER BY note_time DESC;";
                   break;
//                    sql = "select * from note where note_id in (select collect_note from collect where user_id = ?);";break;
                case 2:
                    sql = "select note.*,user_name ,head_portrait ," +
                            "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                            "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                            "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                            " from note_view as note,`user`" +
                            "WHERE note.user_id = `user`.user_id and note.note_id  in (select love_note from love where user_id = ?) ORDER BY note_time DESC;";
                    break;
                 //  sql="select * from note where note_id in (select love_note from love where user_id = ?);";break;
                case 3:
                    sql = "select note.*,user_name ,head_portrait ," +
                            "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                            "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                            "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                            " from note_view as note,`user`" +
                            " WHERE note.user_id = `user`.user_id and " +
                            " note.user_id  in (select concern_id from follow where user_id = ?)  ORDER BY note_time DESC;";
                    break;
//                    sql="select * from note where user_id  in (select concern_id from follow where user_id = ?);";break;
                    default:
                        sql =
                                "select note.*,user_name ,head_portrait ," +
                                        "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                                        "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                                        "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                                        " from note_view as note,`user`" +
                                        "WHERE note.user_id = `user`.user_id   and  (title like ? or `user`.address like ? )  ORDER BY note_time DESC;";
//                        sql="select * from note";

            }
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
//
                pres.setInt(1, Constants.user_id);
                pres.setInt(2, Constants.user_id);
                pres.setInt(3, Constants.user_id);
                if(num>3){
                    pres.setString(4,"%"+Constants.user_address+"%");
                    pres.setString(5,"%"+Constants.user_address+"%");
                }
                else
                pres.setInt(4,Constants.user_id);
                ResultSet res = pres.executeQuery();
                while(res.next())
                {
                    Note note = new Note();
                    note.setNote_id(res.getInt("note_id"));
                    note.setUser_id(res.getInt("user_id"));
                    note.setNote_time(res.getDate("note_time"));
                    note.setTitle(res.getString("title"));
                    note.setText(res.getString("text"));
                    note.setType(res.getString("type"));
                    note.setNote_content(res.getString("note_context"));
                    note.setNum_love(res.getInt("num_love"));
                    note.setNum_collect(res.getInt("num_collect"));
                    note.setNum_comment(res.getInt("num_comment"));

                    note.setHead_portrait(res.getString("head_portrait"));
                    note.setUser_name(res.getString("user_name"));
                    note.setIf_follow(res.getInt("if_follow"));
                    note.setIf_collect(res.getInt("if_collect"));
                    note.setIf_love(res.getInt("if_love"));

                    notes.add(note);
                }
                return  notes;
            } catch (SQLException e) {
                return null;
            }
        }
    }

    public boolean add_note(Note note) {                    //添加一条笔记
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {
            String sql = "insert into note values(?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1,note.getNote_id());
                pre.setInt(2,note.getUser_id());
                pre.setDate(3, (Date) note.getNote_time());
                pre.setString(4,note.getTitle());
                pre.setString(5,note.getType());
                pre.setString(6,note.getText());
                pre.setString(7,note.getNote_content());
                pre.setInt(8,note.getNum_love());
                pre.setInt(9,note.getNum_collect());
                pre.setInt(10,note.getNum_comment());
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
        }}



    public boolean love(Note note) {                    //实现点赞或取消赞
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {
            String sql;
            if (note.getIf_love()==0)               //如果没点赞
            {
                note.setIf_love(1);
                sql = "insert into love values(?,?)";

            }
            else {
                note.setIf_love(0);
                sql = "delete from love where user_id =? and love_note = ?";

            }

            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(2,note.getNote_id());
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
        }}


    public boolean collect(Note note) {                    //实现收藏或取消收藏
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {
            String sql;
            if (note.getIf_collect()==0)               //如果没收藏
            {
                note.setIf_collect(1);
                sql = "insert into collect values(?,?)";

            }
            else {
                note.setIf_collect(0);
                sql = "delete from collect where user_id =? and collect_note = ?";

            }

            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(2,note.getNote_id());
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
        }}

    public boolean follow(Note note) {                    //实现关注或取消关注用户
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {
            String sql;
            if (note.getIf_follow()==0)               //如果没关注
            {
                note.setIf_follow(1);
                sql = "insert into follow values(?,?)";

            }
            else {
                note.setIf_follow(0);
                sql = "delete from follow where user_id =? and concern_id = ?";

            }

            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(2,note.getUser_id());
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
        }}

    public List<Comment> find_comment_by_note(int note_id){                          //查找该笔记的所有评论
        List<Comment> comments = new ArrayList<>();

        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return null;
        } else {
            Log.i(TAG,"register:com is not null");
//            String sql = "select * from note ";
            String sql =
                    "select `comment`.*,user_name,head_portrait  from comment,`user`  where `comment`.user_id=`user`.user_id  and note_id =? ORDER BY comment_time DESC ";

            try {

                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setInt(1, note_id);
                ResultSet res = pres.executeQuery();
                while(res.next())
                {
                    Log.i(TAG,"获取数据+1");
                    Comment comment = new Comment();
                    comment.setNote_id(res.getInt("note_id"));
                    comment.setUser_id(res.getInt("user_id"));
                    comment.setComment_text( res.getString("comment_content"));
                    comment.setComment_time(res.getDate("comment_time"));
                    comment.setUser_name(res.getString("user_name"));
                    comment.setUser_head(res.getString("head_portrait"));
                    comments.add(comment);

                }
                return  comments;
            } catch (SQLException e) {
                return null;
            }
        }
    }


    public boolean add_Comment(int note_id,int user_id, String comment_content) {                    //添加一条评论
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {
            long l = System.currentTimeMillis();
//            Time  time = new java.util.Timer();
//            java.util.Date date = new java.util.Date(l);

//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//            Date date = new Date();// new Date()为获取当前系统时间
//            SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
            Date curDate =  new Date(System.currentTimeMillis());
//            String   date   =   formatter.format(curDate);

            String sql = "insert into comment values(?,?,?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1,note_id);
                pre.setInt(2,user_id);
                pre.setString(3,comment_content);
                pre.setDate(4 ,curDate);
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
        }}

    public Note find_last_or_next_note(int note_id,boolean bool){                          //通过id查找上下笔记
        List<Note> notes = new ArrayList<>();
        Note note = new Note();
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return null;
        } else {
            String sql;
            if(bool==false){
                sql =
                        "select note.*,user_name ,head_portrait ," +
                                "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                                "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                                "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                                " from note_view as note,`user`" +
                                "WHERE note.user_id = `user`.user_id and note.note_id > ?  ORDER BY note_time ;";
            }
            else {
                sql =
                        "select note.*,user_name ,head_portrait ," +
                                "(select  count(*) from follow where user_id=? AND concern_id=`user`.user_id) as if_follow," +
                                "(select  count(*) from love where user_id=? AND love_note =note.note_id) as if_love," +
                                "(select  count(*) from collect where user_id=? AND collect_note = note.note_id) as if_collect" +
                                " from note_view as note,`user`" +
                                "WHERE note.user_id = `user`.user_id and note.note_id < ?  ORDER BY note_time  DESC;";

            }


            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setInt(1, Constants.user_id);
                pres.setInt(2, Constants.user_id);
                pres.setInt(3, Constants.user_id);
                pres.setInt(4,note_id);
                ResultSet res = pres.executeQuery();
                if (res.next())
                {

                    note.setNote_id(res.getInt("note_id"));
                    note.setUser_id(res.getInt("user_id"));
                    note.setNote_time(res.getDate("note_time"));
                    note.setTitle(res.getString("title"));
                    note.setText(res.getString("text"));
                    note.setType(res.getString("type"));
                    note.setNote_content(res.getString("note_context"));
                    note.setNum_love(res.getInt("num_love"));
                    note.setNum_collect(res.getInt("num_collect"));
                    note.setNum_comment(res.getInt("num_comment"));
                    note.setHead_portrait(res.getString("head_portrait"));


                    note.setUser_name(res.getString("user_name"));
                    note.setIf_follow(res.getInt("if_follow"));
                    note.setIf_collect(res.getInt("if_collect"));
                    note.setIf_love(res.getInt("if_love"));


                }
                return  note;
            } catch (SQLException e) {
                return null;
            }
        }
    }

    public boolean delete_note(int note_id) {
        if(conn==null) {
            Log.i(TAG,"register:com is null");
            return false;
        } else {


            String sql = "delete from note where note_id = ?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1,note_id);
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
        }}
}