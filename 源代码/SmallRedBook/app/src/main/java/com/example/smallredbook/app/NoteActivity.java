package com.example.smallredbook.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.smallredbook.R;
import com.example.smallredbook.base.CommentAdapter;
import com.example.smallredbook.base.NoteImgAdapter;
import com.example.smallredbook.model.Comment;
import com.example.smallredbook.model.Note;
import com.example.smallredbook.model.NoteDao;
import com.example.smallredbook.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.core.os.LocaleListCompat.create;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_return,iv_share,iv_user_head,iv_message,iv_collect,iv_love,iv_comment_close,iv_comment_open;
    private TextView tv_note_title,tv_user_name,tv_watch,tv_commit,tv_num_message,tv_num_collect,tv_num_love,tv_note_text,tv_send,tv_comment_text;
    private VideoView vv_note_video;
    private RecyclerView rv_comment;
    private EditText et_comment;
    private LinearLayout ll_comment;
    private ViewPager vp_note_img;
    private float x1=0,x2=0,y1 = 0,y2=0;
    AlertDialog alertDialog;
    Layout layout_comment;
    MediaController mediaController;
    private List<Note>  notes = new ArrayList<>();
    private List<Comment>  comments = new ArrayList<>();
    Note note;
    int  note_id  = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setStatusBar();
//        requestWindowFeature(
//                Window.FEATURE_NO_TITLE
//        );
        Intent intent = getIntent();

       String id = intent.getStringExtra("note_id");
       if(id!=null){
           note_id = Integer.parseInt(id);
       }


        iv_collect = findViewById(R.id.iv_collect);
        iv_love= findViewById(R.id.iv_love);
        iv_message= findViewById(R.id.iv_message);
        iv_return = findViewById(R.id.iv_return);
        iv_share = findViewById(R.id.iv_share);
        iv_user_head = findViewById(R.id.iv_user_head);
        iv_comment_open =findViewById(R.id.iv_comment_open);
        tv_commit = findViewById(R.id.tv_commit);
        tv_note_title = findViewById(R.id.tv_note_title);
        tv_num_message = findViewById(R.id.tv_num_message);
        tv_num_love = findViewById(R.id.tv_num_love);
        tv_num_collect = findViewById(R.id.tv_num_collect);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_watch = findViewById(R.id.tv_watch);
        tv_note_text = findViewById(R.id.tv_note_text);
        vv_note_video = findViewById(R.id.vv_note_video);
        tv_send = findViewById(R.id.tv_send);
        et_comment = findViewById(R.id.et_comment);
        iv_comment_close = findViewById(R.id.iv_comment_close);
        rv_comment = findViewById(R.id.rv_comment);
        ll_comment = findViewById(R.id.ll_comment);
        vp_note_img = findViewById(R.id.vp_note_img);

//        tv_comment_text = findViewById(R.id.tv_comment_text);


        iv_collect.setOnClickListener(this);
        iv_love.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        iv_return.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        iv_user_head.setOnClickListener(this);
        tv_watch.setOnClickListener(this);
        tv_user_name.setOnClickListener(this);
        tv_commit.setOnClickListener(this);
        rv_comment.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        tv_note_text.setMovementMethod(ScrollingMovementMethod.getInstance());
        iv_comment_open.setOnClickListener(this);
        tv_send.setOnClickListener(this);
        iv_comment_close.setOnClickListener(this);


        vv_note_video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                onTouchEvent(motionEvent);
                return false;
            }
        });

        vp_note_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                onTouchEvent(motionEvent);
                return false;
            }
        });


        initView();
        init_note();
    }

    private void initView() {
    Log.i("测试结果","测试1");
      new Thread(new Runnable() {                     //创建一个子线程
           @Override
           public void run() {
                Log.i("测试结果","测试2");
               Looper.prepare();
                NoteDao noteDao= new NoteDao();                 //创建一个noteDao
                 note = noteDao.find_note_by_id(note_id);           //根据id获取一个笔记
                    Log.i("测试结果","数据"+note.getNote_content());
                    final String user_name = note.getUser_name();
                    final String note_title = note.getTitle();
                    final String note_text = note.getText();
                    final String num_collect = Integer.toString(note.getNum_collect());
                    final String num_love =Integer.toString(note.getNum_love());
                    final String num_message = Integer.toString(note.getNum_comment());
               final String vedio_name = note.getNote_content();

               final String user_head = Constants.User_URL+note.getHead_portrait();
               final int if_collect = note.getIf_collect();
               final int if_love = note.getIf_love();
               final int if_follow = note.getIf_follow();

               runOnUiThread(new Runnable() {               //返回主线程执行下面语句
                   @Override
                   public void run() {
                       tv_user_name.setText(user_name);
                       tv_note_title.setText(note_title);
                       tv_note_text.setText(note_text);
                       tv_num_collect.setText(num_collect);
                       tv_num_love.setText(num_love);
                       tv_num_message.setText(num_message);

                       Glide.with(NoteActivity.this).load(user_head).into(iv_user_head);
                       if(if_love>0)
                       {
                           Glide.with(NoteActivity.this).load(R.drawable.love).into(iv_love);
                       }
                       if(if_collect>0)
                       {
                           Glide.with(NoteActivity.this).load(R.drawable.collect_red).into(iv_collect);
                       }
                       if(if_follow>0)
                       {
                           tv_watch.setText("已关注");
                       }


                       if(vedio_name.indexOf("mp4")==-1){                   //判断视频还是图片
                           List<String >  lists = Arrays.asList(vedio_name.split(","));
                           init_img(lists);         // 匹配图片
                       }
                       else      init_vedio(vedio_name);              //匹配小视频

                   }
               });


               Looper.loop();

           }
       }).start();
        Log.i("测试结果","测试3");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.iv_return:
                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.iv_collect:
                if (note.getIf_collect()>0)
                {
                    Glide.with(NoteActivity.this).load(R.drawable.collect).into(iv_collect);
                    tv_num_collect.setText(Integer.toString(Integer.parseInt(tv_num_collect.getText().toString())-1));
                }

                else
                {
                    Glide.with(NoteActivity.this).load(R.drawable.collect_red).into(iv_collect);
                    tv_num_collect.setText(Integer.toString(Integer.parseInt(tv_num_collect.getText().toString())+1));
                }
                collect_note(note);
                break;
            case R.id.iv_love:
                if (note.getIf_love()>0)
                {
                    Glide.with(NoteActivity.this).load(R.drawable.love_no).into(iv_love);
                   tv_num_love.setText(Integer.toString(Integer.parseInt(tv_num_love.getText().toString())-1));
                }

                else
                {
                    Glide.with(NoteActivity.this).load(R.drawable.love).into(iv_love);
                    tv_num_love.setText(Integer.toString(Integer.parseInt(tv_num_love.getText().toString())+1));
                }
                love_note(note);

                break;
            case R.id.iv_message:
                find_comment(note.getNote_id());

//                Toast.makeText(this, "评论", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_comment_close:

                ll_comment.setVisibility(View.GONE);
//                Toast.makeText(this, "关闭评论", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_user_head:
                Toast.makeText(this, "个人中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_watch:
                if (note.getIf_follow()>0)
                    tv_watch.setText("关注");
                else
                    tv_watch.setText("已关注");
                follow_user(note);
                break;
            case  R.id.tv_user_name:
                Toast.makeText(this, "个人中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_commit  :
//                Toast.makeText(this, "评论", Toast.LENGTH_SHORT).show();
                if(et_comment.getVisibility()==View.VISIBLE) {
                    et_comment.setVisibility(View.GONE);
                    tv_send.setVisibility(View.GONE);
                    tv_num_message.setVisibility(View.VISIBLE);
                    iv_message.setVisibility(View.VISIBLE);
                }
                else {
                    et_comment.setVisibility(View.VISIBLE);
                    tv_send.setVisibility(View.VISIBLE);
                    tv_num_message.setVisibility(View.INVISIBLE);
                    iv_message.setVisibility(View.INVISIBLE);
//                    et_comment.setFocusable(true);
//                    et_comment.setFocusableInTouchMode(true);
//                    et_comment.requestFocus();
                    showInputTips(et_comment);
//                    showSoftInputFromWindow(NoteActivity.this,et_comment);
                }
                break;

            case R.id.iv_comment_open  :
                Toast.makeText(this, "评论", Toast.LENGTH_SHORT).show();
                if(et_comment.getVisibility()==View.VISIBLE) {
                    et_comment.setVisibility(View.GONE);
                    tv_send.setVisibility(View.GONE);
                    tv_num_message.setVisibility(View.VISIBLE);
                    iv_message.setVisibility(View.VISIBLE);

                }
                else {
                    et_comment.setVisibility(View.VISIBLE);
                    tv_send.setVisibility(View.VISIBLE);
                    tv_num_message.setVisibility(View.INVISIBLE);
                    iv_message.setVisibility(View.INVISIBLE);
//                    showSoftInputFromWindow(NoteActivity.this,et_comment);
                    showInputTips(et_comment);
                }
                break;
            case  R.id.tv_send:

                if(et_comment.getText()!=null&&et_comment.getText().toString()!=""){
                    add_comment(note.getNote_id(),Constants.user_id,et_comment.getText().toString());
                    et_comment.setText("");
                    et_comment.setVisibility(View.GONE);
                    tv_send.setVisibility(View.GONE);
                    tv_num_message.setText(Integer.toString(Integer.parseInt(tv_num_message.getText().toString())+1));
                    tv_num_message.setVisibility(View.VISIBLE);
                    iv_message.setVisibility(View.VISIBLE);

                    new Thread(){
                        @Override
                        public void run() {
                            Instrumentation instrumentation = new Instrumentation();
                            instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                        }
                    }.start();
//                    hideSoftKeyboard(NoteActivity.this);

                }

                break;



        }
    }
    private void init_vedio(String vedio_name) {        //加载视频
        Log.i("测试结果","测试4");
        mediaController = new MediaController(this);

     mediaController.setVisibility(View.INVISIBLE);        //隐藏进度条
        String uri= Constants.Note_URL+vedio_name;

        // String uri = "android.resource://" + getPackageName() + "/" + R.raw.aas;
        vv_note_video.setVideoURI(Uri.parse(uri));
        vv_note_video.setMediaController(mediaController);
        mediaController.setMediaPlayer(vv_note_video);
        vv_note_video.requestFocus();
        vv_note_video.start();
        vv_note_video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mediaController.setVisibility(View.VISIBLE);        //隐藏进度条
                return false;
            }
        });
//        mediaController.setVisibility(View.VISIBLE);        //隐藏进度条
        vv_note_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
    }


    private void init_img(List<String> lists){            //加载多张图片
        vv_note_video.setVisibility(View.GONE);
        vp_note_img.setVisibility(View.VISIBLE);
//        lists.add("1.jpg");
//        lists.add("2_2.jpg");
//        lists.add("2.jpg");
        vp_note_img.setAdapter(new NoteImgAdapter(NoteActivity.this,lists));






    }


    public boolean init_note(){
        Log.i("测试结果","测试1");
       new Thread(new Runnable() {                     //创建一个子线程
           @Override
           public void run() {
                Log.i("测试结果","测试2");
               Looper.prepare();
                NoteDao noteDao= new NoteDao();                 //创建一个noteDao
                notes = noteDao.find_all_note();          //根据id获取一个笔记
               Log.i("测试结果", "note数量"+String.valueOf(notes.size()));
                Looper.loop();

           }

       }).start();

        return true;


    }

    public static void showSoftInputFromWindow(Activity activity, EditText editText) {              //获取输入框焦点
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }
    private void love_note(final Note note) {              //点赞或取消赞
        new Thread( ){            //创建一个子线程
            public void run() {
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                noteDao.love(note);
            };
        }.start();
    }

    private void collect_note(final Note note) {              //收藏
        new Thread( ){            //创建一个子线程
            public void run() {
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                noteDao.collect(note);
            };
        }.start();
    }

    private void follow_user(final Note note) {              //关注用户
        new Thread( ){            //创建一个子线程
            public void run() {
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                noteDao.follow(note);
            };
        }.start();
    }

    private void find_comment(int noe_id){

            new Thread( ){            //创建一个子线程
                public void run() {
                    NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                    comments = noteDao.find_comment_by_note(note_id);



    runOnUiThread(new Runnable() {               //返回主线程执行下面语句
        @Override
        public void run() {

            ll_comment.setVisibility(View.VISIBLE);

            if(comments!=null){
                rv_comment.setAdapter(new CommentAdapter(NoteActivity.this,comments));
            }

        }
    });

                };
            }.start();
        }
    private void add_comment(final int note_id, final int user_id, final String comment_text){

        new Thread( ){            //创建一个子线程
            public void run() {
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                noteDao.add_Comment(note_id,user_id,comment_text);
            };
        }.start();
    }

    public static void hideSoftKeyboard(Activity activity) {        //隐藏软键盘
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    private void showInputTips(EditText et_text) {
        et_text.setFocusable(true);
        et_text.setFocusableInTouchMode(true);
        et_text.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) et_text.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(et_text, 0);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();

        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候

            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 200) {
                find_last_or_next_note(note.getUser_id(),true);
//                Toast.makeText(NoteActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 200) {
                find_last_or_next_note(note.getUser_id(),false);
//                Toast.makeText(NoteActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            }
        }


        return super.onTouchEvent(event);
    }

    public boolean find_last_or_next_note(int user_id, final Boolean bool){

        new Thread(new Runnable() {                     //创建一个子线程
            @Override
            public void run() {

                Looper.prepare();
                NoteDao noteDao= new NoteDao();                 //创建一个noteDao

                if(noteDao.find_last_or_next_note(note.getNote_id(),bool)==null||noteDao.find_last_or_next_note(note.getNote_id(),bool).getNote_content()==null
                        ||noteDao.find_last_or_next_note(note.getNote_id(),bool).getNote_content()==" "
                ){
                    return;
                }
                else{
                    note = noteDao.find_last_or_next_note(note.getNote_id(),bool) ;         //根据id获取一个笔记

                    final String user_name = note.getUser_name();
                    final String note_title = note.getTitle();
                    final String note_text = note.getText();
                    final String num_collect = Integer.toString(note.getNum_collect());
                    final String num_love =Integer.toString(note.getNum_love());
                    final String num_message = Integer.toString(note.getNum_comment());
                    final String vedio_name = note.getNote_content();

                    final String user_head = Constants.User_URL+note.getHead_portrait();
                    final int if_collect = note.getIf_collect();
                    final int if_love = note.getIf_love();
                    final int if_follow = note.getIf_follow();

                    runOnUiThread(new Runnable() {               //返回主线程执行下面语句
                        @Override
                        public void run() {
                            tv_user_name.setText(user_name);
                            tv_note_title.setText(note_title);
                            tv_note_text.setText(note_text);
                            tv_num_collect.setText(num_collect);
                            tv_num_love.setText(num_love);
                            tv_num_message.setText(num_message);

                            Glide.with(NoteActivity.this).load(user_head).into(iv_user_head);
                            if(if_love>0)
                            {
                                Glide.with(NoteActivity.this).load(R.drawable.love).into(iv_love);
                            }
                            if(if_collect>0)
                            {
                                Glide.with(NoteActivity.this).load(R.drawable.collect_red).into(iv_collect);
                            }
                            if(if_follow>0)
                            {
                                tv_watch.setText("已关注");
                            }

                            try{

                                if(vedio_name.indexOf("mp4")==-1){                   //判断视频还是图片
                                    List<String >  lists = Arrays.asList(vedio_name.split(","));
                                    init_img(lists);         // 匹配图片
                                    vv_note_video.setVisibility(View.GONE);
                                    vp_note_img.setVisibility(View.VISIBLE);
//                                    Toast.makeText(NoteActivity.this, "图片"+vedio_name, Toast.LENGTH_SHORT).show();
                                }
                                else      {
                                    vp_note_img.setVisibility(View.GONE);
                                    vv_note_video.setVisibility(View.VISIBLE);
                                    init_vedio(vedio_name);              //匹配小视频
//                                    Toast.makeText(NoteActivity.this,"视频"+ vedio_name+note.getNote_content(), Toast.LENGTH_SHORT).show();
                                }
                            }catch(Exception ex){
                                Log.i("错误",ex.getMessage());
                            }



                        }
                    });
                }

                Looper.loop();


            }

        }).start();

        return true;


    }
    //改变状态栏的字体颜色
    protected void setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));//设置状态栏颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        }
    };

}
