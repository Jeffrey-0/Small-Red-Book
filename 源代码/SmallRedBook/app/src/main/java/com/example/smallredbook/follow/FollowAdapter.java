package com.example.smallredbook.follow;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smallredbook.R;
import com.example.smallredbook.model.Note;
import com.example.smallredbook.model.UserBean;
import com.example.smallredbook.model.UserDao;
import com.example.smallredbook.utils.Constants;

import java.util.ArrayList;
import java.util.List;

//import com.example.smallredbook.app.ceshi;

public class FollowAdapter extends RecyclerView.Adapter{
    @NonNull
    private Context mContext;
    private AdapterView.OnItemClickListener mListener;
    private List<String> head_list = new ArrayList<>();
    private List<String>  name_list = new ArrayList<>();
    private List<UserBean>  users = new ArrayList<>();
    private List<Note>  notes = new ArrayList<>();

    public FollowAdapter(Context context){
        mContext = context;

    }

    public FollowAdapter(Context context, List<UserBean>  users){
        mContext = context;
        this.users = users;

    }


    @Override
    public LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.follow, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindViewHolder((LinearViewHolder)holder,position);
    }

    public void onBindViewHolder(@NonNull final LinearViewHolder holder, final int position) {


        holder.tv_user_name.setText(users.get(position).getUser_name());
         String head_img= Constants.User_URL+users.get(position).getHead();
          Glide.with(mContext).load(head_img).into(holder.iv_head);


          holder.iv_delete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  delete_by_user_id(users.get(position).getUser_id());
                  users.remove(position);
                  notifyDataSetChanged();

              }
          });

    }



    @Override
    public int getItemCount() {
        int num=0;
        try{
            num = users.size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return num;
    }
    class LinearViewHolder extends  RecyclerView.ViewHolder{
        private ImageView iv_head;
        private TextView tv_user_name;
        private ImageView iv_delete;


        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);

      iv_delete = itemView.findViewById(R.id.iv_delete_follow_user);
      tv_user_name = itemView.findViewById(R.id.tv_user_name);
      iv_head = itemView.findViewById(R.id.iv_user_head);


        }
    }



    private void delete_by_user_id(final int user_id){
        new Thread( ){            //创建一个子线程
            public void run() {
//                Log.i("测试结果", "测试2");
//                Message msg = new Message();
//                msg.obj = "子线程111";
                UserDao userDao = new UserDao();
                userDao.delete_follow_user_by_id(user_id);
//                userDao.find_follow_users();
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
                    notifyDataSetChanged();
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
