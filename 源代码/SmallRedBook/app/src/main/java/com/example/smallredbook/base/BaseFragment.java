package com.example.smallredbook.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
* 基类 Fragment
* 首页HomeFragment
* 关注FollowFragment
* 发布PublishFragment
* 消息MessageFragment
* 用户UserFragment
* 都要继承该类
* */
public abstract class BaseFragment  extends Fragment {
    protected Context mContext;  //上下文，即当前的activity

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /*
    * 抽象类，强制由子类实现该方法
    * */
    public abstract View initView() ;


    /*
    * 当Activity创建的时候回调这个方法
    * */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
//        initData();
    }

    /*
    * 当子类需要联网请求数据时，可重写该方法，实现数据的加载
    * */
    public void initData(){

    }






}
