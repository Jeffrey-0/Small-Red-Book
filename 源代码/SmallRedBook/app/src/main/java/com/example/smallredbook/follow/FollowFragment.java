package com.example.smallredbook.follow;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.smallredbook.R;
import com.example.smallredbook.base.BaseFragment;
import com.example.smallredbook.model.UserBean;
import com.example.smallredbook.model.UserDao;

import java.util.ArrayList;
import java.util.List;

/*
* 消息的Fragment
* */
public class FollowFragment extends BaseFragment {
    private TextView textView;
    private RecyclerView rv_follow_users;
   private  List<UserBean> users= new ArrayList<>();
    SwipeToLoadLayout swipeToLoadLayout ;        //上下拉加载数据

    @Override
    public View initView() {                           //初始化fragment要加载的页面，并添加相应的监听事件，返回该页面
        View  view = View.inflate(mContext, R.layout.fragment_message,null);


        swipeToLoadLayout = view.findViewById(R.id.act_recycler_swipe);
//        LinearLayoutManager llm = new LinearLayoutManager(mContext);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToLoadLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeToLoadLayout.setRefreshing(false);
//                        Toast.makeText(mContext, "下拉出来的数据", Toast.LENGTH_SHORT).show();
                        find_all_follow_user();
//                        init_note(index);
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
//                        Toast.makeText(mContext, "下拉出来的数据", Toast.LENGTH_SHORT).show();
                    }
                },2000);
            }
        });

        rv_follow_users = view.findViewById(R.id.swipe_target);
        rv_follow_users.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        find_all_follow_user();

        return view;
    }

    @Override
    public void initData() {                    //加载网络请求的数据，若无数据要求可不用重写
        super.initData();

        rv_follow_users.setAdapter(new FollowAdapter(mContext,users));
//        textView.setText("消息");
    }

    public  void  find_all_follow_user(){
        new Thread( ){            //创建一个子线程
            public void run() {
                Log.i("测试结果", "测试2");
                Message msg = new Message();
                msg.obj = "子线程111";
                UserDao userDao = new UserDao();
                users = userDao.find_follow_users();
                mHander.sendMessage(msg);


            }

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
    public void init_user(){


    }


}
