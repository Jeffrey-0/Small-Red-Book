package com.example.smallredbook.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.method.ScrollingMovementMethod;
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
import com.example.smallredbook.app.NoteActivity;
import com.example.smallredbook.model.Comment;
import com.example.smallredbook.model.Note;
import com.example.smallredbook.model.NoteDao;
import com.example.smallredbook.utils.Constants;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

//import com.example.smallredbook.app.ceshi;

public class CommentAdapter extends RecyclerView.Adapter{
    @NonNull
    private Context mContext;
    private List<Comment>  comments = new ArrayList<>();

    public CommentAdapter(Context context,  List<Comment>  comments){
        mContext = context;
        this.comments = comments;
    }


    @Override
    public LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.comment_item, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindViewHolder((LinearViewHolder)holder,position);
    }

    public void onBindViewHolder(@NonNull final LinearViewHolder holder, final int position) {

        holder.tv_comment_user_name.setText( comments.get(position).getUser_name());
       // holder.tv_comment_time.setText((CharSequence) comments.get(position).getComment_time());
        String comment_time = new SimpleDateFormat("yyyy-MM-dd").format(comments.get(position).getComment_time());
        holder.tv_comment_time.setText(comment_time);

        holder.tv_comment_text.setText(comments.get(position).getComment_text());
        String head_img = Constants.User_URL + comments.get(position).getUser_head();
          Glide.with(mContext).load(head_img).into(holder.iv_user_head);



    }



    @Override
    public int getItemCount() {
        int num=0;
        try{
            num = comments.size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return num;
    }
    class LinearViewHolder extends  RecyclerView.ViewHolder{

       private TextView tv_comment_user_name;
       private TextView  tv_comment_time;
       private TextView tv_comment_text;
       private ImageView iv_user_head;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_comment_text = itemView.findViewById(R.id.tv_comment_text);
            iv_user_head = itemView.findViewById(R.id.iv_comment_user_head);
            tv_comment_time = itemView.findViewById(R.id.tv_comment_time);
            tv_comment_user_name = itemView.findViewById(R.id.tv_comment_user_name);


        }


    }






}
