package com.example.smallredbook.base;

import android.content.res.ColorStateList;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.smallredbook.R;
import com.example.smallredbook.home.HomeAdapter;
import com.example.smallredbook.model.Note;
import com.example.smallredbook.model.NoteDao;
import com.example.smallredbook.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/*
* 主页面中的三个页面fragment（发现、关注、附近）
* */
public class TabFragment extends BaseFragment {
    private int index =0;
   SwipeToLoadLayout swipeToLoadLayout ;        //上下拉加载数据
    private TextView tv_title;
    private List<Note>  notes;
    private RecyclerView rv_note;
    private View view;
    //这个构造方法是便于各导航同时调用一个fragment
   public TabFragment(List<Note>  notes,int index){
       mContext = getActivity();
       this.notes = notes;
       this.index = index;
  }

    public TabFragment(List<Note>  notes){
        mContext = getActivity();
        this.notes = notes;

    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home_tab0,null);
        this.view =View.inflate(mContext, R.layout.fragment_home_tab0,null);
      swipeToLoadLayout = view.findViewById(R.id.act_recycler_swipe);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToLoadLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeToLoadLayout.setRefreshing(false);
//                        Toast.makeText(mContext, "下拉出来的数据", Toast.LENGTH_SHORT).show();
                        init_note(index);
                    }
                },2000);

            }
        });
//
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                swipeToLoadLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeToLoadLayout.setLoadingMore(false);
                        Toast.makeText(mContext, "已到达底部", Toast.LENGTH_SHORT).show();
                    }
                },2000);
            }
        });



        rv_note =view.findViewById(R.id.swipe_target);
        rv_note.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rv_note.setAdapter(new NoteAdapter(mContext,notes));


//        swipeToLoadLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                swipeToLoadLayout.setRefreshing(true);
//            }
//        });


        return view;
    }

    @Override
    public void initData() {
        super.initData();


//        View view = View.inflate(mContext, R.layout.fragment_home_tab0,null);
//        swipeToLoadLayout = view.findViewById(R.id.act_recycler_swipe);
//        LinearLayoutManager llm = new LinearLayoutManager(mContext);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);

//        rv_note =view.findViewById(R.id.swipe_target);
        rv_note.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rv_note.setAdapter(new NoteAdapter(mContext,notes));


//rv_note.setVisibility(View.VISIBLE);


    }



    private void init_note(final int index) {              //根据当前页面获取相应的笔记信息
        new Thread( ){            //创建一个子线程
            public void run() {
                Log.i("测试结果", "测试2");
                Message msg = new Message();
                msg.obj = "子线程111";
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao

                switch(index){
                    case 10:
                       notes = noteDao.find_all_note();           //获取所有笔记
//                        notes = noteDao.find_my_note_by_type(Constants.user_id,3);
                        mHander.sendMessage(msg);
                        break;
                    case 12:
                        notes = noteDao.find_my_note_by_type(Constants.user_id,3);
                        mHander.sendMessage(msg);
                        break;
                    case 13:
                        notes = noteDao.find_my_note_by_type(Constants.user_id,4);
                        mHander.sendMessage(msg);
                        break;

                    case 20:
                        notes = noteDao.find_all_note();
                        mHander.sendMessage(msg);
                        break;
                    case 21:
                        notes = noteDao.find_note_by_type("娱乐");
                        mHander.sendMessage(msg);
                        break;
                    case 22:
                        notes = noteDao.find_note_by_type("生活");
                        mHander.sendMessage(msg);
                        break;
                    case 23:
                        notes = noteDao.find_note_by_type("运功");
                        mHander.sendMessage(msg);
                        break;
                    case 24:
                        notes = noteDao.find_note_by_type("穿搭");
                        mHander.sendMessage(msg);
                        break;
                    case 25:
                        notes = noteDao.find_note_by_type("美食");
                        mHander.sendMessage(msg);
                        break;
                    case 26:
                        notes = noteDao.find_note_by_type("动漫");
                        mHander.sendMessage(msg);
                        break;
                    case 27:
                        notes = noteDao.find_note_by_type("学习");
                        mHander.sendMessage(msg);
                        break;
                    case 28:
                        notes = noteDao.find_note_by_type("搞笑");
                        mHander.sendMessage(msg);
                        break;
                    case 29:
                        notes = noteDao.find_note_by_type("其他");
                        mHander.sendMessage(msg);
                        break;
                    case 31:
                        notes = noteDao.find_my_note_by_type(Constants.user_id,0);
                        mHander.sendMessage(msg);
                        break;
                    case 32:
                        notes = noteDao.find_my_note_by_type(Constants.user_id,1);
                        mHander.sendMessage(msg);
                        break;
                    case 33:
                        notes = noteDao.find_my_note_by_type(Constants.user_id,2);
                        mHander.sendMessage(msg);
                        break;

                }



            };
        }.start();

    }

    Handler mHander = new Handler(){             //创建一个主线程监听，当子线程init_note（）执行完毕后，执行下面initData（）方法；
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    Log.i("获取子线程","获取子线程"+msg.obj);
                    initData();
//                    initView();
                    break;

                default:
//                    initData();
//                    Log.i("主页刷新数据","刷新");

                    break;
            }

        }
    };




}
