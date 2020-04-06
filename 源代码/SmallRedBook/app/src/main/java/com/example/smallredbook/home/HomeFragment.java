package com.example.smallredbook.home;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.example.smallredbook.R;
import com.example.smallredbook.app.MainActivity;
import com.example.smallredbook.app.SearchActivity;
import com.example.smallredbook.base.BaseFragment;
import com.example.smallredbook.base.TabFragment;
import com.example.smallredbook.model.Note;
import com.example.smallredbook.model.NoteDao;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/*
* 主页的Fragment
* */
public class HomeFragment extends BaseFragment {
    private ViewPager pager;
    private HomeAdapter homeAdapter;
    private List<TabFragment> fragmentList;
    private TabLayout tabLayout;
    private  List<String> mTitle;
    private  String [] title={"发现","关注","附近"};
    private TextView et_search;
    private List<Note>  notes,notes2,notes3;
    private int user_id = 111;
    private    boolean  pass = false;
private  Handler childHandler;

    @Override
    public View initView() {                           //初始化fragment要加载的页面，并添加相应的监听事件，返回该页面
        View  view = View.inflate(mContext, R.layout.fragment_home,null);
        pager = view.findViewById(R.id.VP_content);
        tabLayout = view.findViewById(R.id.TL_top_bar);
        et_search = view.findViewById(R.id.ET_search);
        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
            }
        });
        init_note();
        return view;
    }




    private boolean init_note() {              //获取笔记信息
        new Thread( ){            //创建一个子线程
            public void run() {
                Log.i("测试结果", "测试2");
                Message msg = new Message();
                msg.obj = "子线程111";
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                notes = noteDao.find_all_note();           //获取所有笔记
//                notes = noteDao.find_my_note_by_type(user_id,5);
                notes2 = noteDao.find_my_note_by_type(user_id,3);      //获取自己关注的所有用户的笔记
                notes3 = noteDao.find_my_note_by_type(user_id,4);      //获取与自己同个城市的所有笔记
           //     Log.i("测试结果", "home笔记数量" + notes.size());
                pass = true;
                mHander.sendMessage(msg);
            };
        }.start();

        return pass;
    }

    Handler mHander = new Handler(){             //创建一个主线程监听，当子线程init_note（）执行完毕后，执行下面initData（）方法；
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    Log.i("获取子线程","获取子线程"+msg.obj);
                    initData();
                    break;
                default:break;
            }

        }
    };


    @Override
    public void initData() {                    //加载网络请求的数据，若无数据要求可不用重写
                         super.initData();
                        fragmentList = new ArrayList<>();
                        mTitle = new ArrayList<>();
                        for(int i= 0;i<title.length;i++)
                        {
                            mTitle.add(title[i]);

                        }
                         fragmentList.add(new TabFragment(notes,10));
                         fragmentList.add(new TabFragment(notes2,12));
                         fragmentList.add(new TabFragment(notes3,13));

                         homeAdapter = new HomeAdapter(this.getChildFragmentManager(),fragmentList,mTitle);
//                        homeAdapter = new HomeAdapter(getActivity().getSupportFragmentManager(),fragmentList,mTitle);
                        pager.setAdapter(homeAdapter);
                        tabLayout.setTabRippleColor(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorWhite)));
                        tabLayout.setupWithViewPager(pager);


                    }



                };











