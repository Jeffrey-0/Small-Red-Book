package com.example.smallredbook.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Note {
    private  int note_id;
    private int user_id;
    private Date note_time;
    private String title;
    private String type;
    private String text;
    private String note_content;
    private  int num_love;
    private int num_collect;
    private int num_comment;
    private String user_name;
    private String head_portrait;
    private int if_follow;
    private int if_love;
    private int if_collect;



    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setNote_time(Date note_time) {
        this.note_time = note_time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public int getIf_follow() {
        return if_follow;
    }

    public void setIf_follow(int if_follow) {
        this.if_follow = if_follow;
    }

    public int getIf_love() {
        return if_love;
    }

    public void setIf_love(int if_love) {
        this.if_love = if_love;
    }

    public int getIf_collect() {
        return if_collect;
    }

    public void setIf_collect(int if_collect) {
        this.if_collect = if_collect;
    }

    public void setNum_love(int num_love) {
        this.num_love = num_love;
    }

    public void setNum_collect(int num_collect) {
        this.num_collect = num_collect;
    }

    public void setNum_comment(int num_comment) {
        this.num_comment = num_comment;
    }

    public int getNote_id() {
        return note_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getNote_time() {
        return note_time;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getNote_content() {
        return note_content;
    }

    public int getNum_love() {
        return num_love;
    }

    public int getNum_collect() {
        return num_collect;
    }

    public int getNum_comment() {
        return num_comment;
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
