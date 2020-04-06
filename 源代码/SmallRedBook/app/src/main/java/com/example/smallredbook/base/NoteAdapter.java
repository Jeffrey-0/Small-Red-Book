package com.example.smallredbook.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.smallredbook.R;
import com.example.smallredbook.app.NoteActivity;
//import com.example.smallredbook.app.ceshi;
import com.example.smallredbook.model.Note;
import com.example.smallredbook.model.NoteDao;
import com.example.smallredbook.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter{
    @NonNull
    private Context mContext;
    private AdapterView.OnItemClickListener mListener;
    private List<String>  list = new ArrayList<>();
    private List<Note>  notes = new ArrayList<>();

//    public NoteAdapter(Context context){
//        mContext = context;
//
//        for(int i =0;i<20;i++){
//            list.add(String.format("%s-%s",i/10+1,i));
//        }
//
//    }
    public NoteAdapter(Context context,List<Note> notes){
        mContext = context;
        this.notes = notes;


    }


    @Override
    public LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.note_item, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindViewHolder((LinearViewHolder)holder,position);
    }

    public void onBindViewHolder(@NonNull final LinearViewHolder holder, final int position) {

        holder.tv_note_title.setText( notes.get(position).getTitle());
        holder.tv_user_name.setText(notes.get(position).getUser_name());
        holder.tv_note_like.setText(Integer.toString(notes.get(position).getNum_love()));
         String head_img= Constants.User_URL+notes.get(position).getHead_portrait();
//         holder.iv_user_head.setImageURI(new Uri(head_img));
          Glide.with(mContext).load(head_img).into(holder.iv_user_head);

          if(notes.get(position).getUser_id()==Constants.user_id)   holder.iv_note_delete.setVisibility(View.VISIBLE);
         if(notes.get(position).getIf_love()>0)
         {
             Glide.with(mContext).load(R.drawable.love).into(holder.iv_note_like);

         }
        holder.iv_note_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (notes.get(position).getIf_love()>0)
                {
                    Glide.with(mContext).load(R.drawable.love_no).into(holder.iv_note_like);
                    holder.tv_note_like.setText(Integer.toString(Integer.parseInt(holder.tv_note_like.getText().toString())-1));
                }

                else
                {
                    Glide.with(mContext).load(R.drawable.love).into(holder.iv_note_like);
                    holder.tv_note_like.setText(Integer.toString(Integer.parseInt(holder.tv_note_like.getText().toString())+1));
                }

                love_note(notes.get(position));
            }
        });

//        holder.iv_user_head.setImageURI();
        String uri= Constants.Note_URL+notes.get(position).getNote_content();
        if(notes.get(position).getNote_content().indexOf("mp4")==-1){                   //判断视频还是图片
            List<String >  lists = Arrays.asList(notes.get(position).getNote_content().split(","));
            Glide.with(mContext).load( Constants.Note_URL+lists.get(0)).into(holder.imageView);
        }
        else     {
            Bitmap bitmap = getNetVideoBitmap(uri);
            holder.imageView.setImageBitmap(bitmap);//对应的ImageView赋值图片
            holder.iv_play.setVisibility(View.VISIBLE);
        }             //匹配小视频





        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NoteActivity.class);
                intent.putExtra("note_id",Integer.toString(notes.get(position).getNote_id()));
                mContext.startActivity(intent);
            }
        });
        holder.iv_note_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int note_id = notes.get(position).getNote_id();
                try{
//                    holder.ll_note.setVisibility(View.GONE);

                  List<Note>  notes_new = new ArrayList<>();
                    for(int i =0;i<notes.size();i++)
                    {
                        if (i!=position)  notes_new.add(notes.get(i));
                    }

//                    notes.remove(position);
                    notes.clear();

                    for (int i=0;i<notes_new.size();i++)
                    {
//                        Log.i("note_new 数量", String.valueOf(notes_new.size())+"更新"+i);
                        notes.add(notes_new.get(i));

                    }
//                    onBindViewHolder((LinearViewHolder)holder,position);
                    Log.i("note_new 数量", String.valueOf(notes_new.size()));
                    Log.i("notes数量", String.valueOf(notes.size()));



                        notifyDataSetChanged();
                }catch(Exception e){
                    Log.i("Log","隐藏note报错"+e.getMessage());
                }

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        NoteDao noteDao = new NoteDao();
//                        Message msg = new Message();
//                        msg.obj = "子线程111";

                        try{
//                            holder.ll_note.setVisibility(View.GONE);
                            noteDao.delete_note(note_id);

//                            notes.clear();
//                            notes =noteDao.find_all_note();
//                            notes.remove(position);
//                            mHander.sendMessage(msg);
                        }catch(Exception e){
                            Log.i("Log",e.getMessage());
                        }

//                        notes.remove(position);

                    }
                }.start();
            }
        });




    }



    @Override
    public int getItemCount() {
        int num=0;
        try{
            num = notes.size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return num;
    }
    class LinearViewHolder extends  RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tv_note_title;
        private TextView tv_user_name;
        private TextView tv_note_like;
        private ImageView iv_user_head;
        private  ImageView iv_play;
        private ImageView iv_note_like;
        private ImageView iv_note_delete;
        private LinearLayout ll_note;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_note_like = itemView.findViewById(R.id.tv_note_like);
            imageView = itemView.findViewById(R.id.iv_note_img);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            iv_user_head = itemView.findViewById(R.id.iv_user_head);
            tv_note_title = itemView.findViewById(R.id.tv_note_title);
            iv_play = itemView.findViewById(R.id.iv_play);
            iv_note_like = itemView.findViewById(R.id.iv_note_like);
            iv_note_delete=itemView.findViewById(R.id.iv_note_delete);
            ll_note = itemView.findViewById(R.id.ll_note);


        }
    }

    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

        retriever.setDataSource(videoUrl,new Hashtable<String, String>());
        //获得第一帧图片
        bitmap = retriever.getFrameAtTime();

        return bitmap;
    }

    private void love_note(final Note note) {              //点赞或取消赞
        new Thread( ){            //创建一个子线程
            public void run() {
//                Log.i("测试结果", "测试2");
//                Message msg = new Message();
//                msg.obj = "子线程111";
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                noteDao.love(note);
            };
        }.start();


    }

    private void new_all_note() {              //点赞或取消赞
        new Thread( ){            //创建一个子线程
            public void run() {
//                Log.i("测试结果", "测试2");
//                Message msg = new Message();
//                msg.obj = "子线程111";
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
               notes.clear();
                notes =noteDao.find_all_note();
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
