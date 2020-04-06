//测试类，用于测试数据库数据的传输，以及访问本地服务器上的图片和视频

package com.example.smallredbook.z_no;

//import android.widget.VideoView;
//
//import com.bumptech.glide.Glide;
//import com.example.smallredbook.R;
//import com.example.smallredbook.model.NoteDao;
//import com.example.smallredbook.model.Note;
//import com.example.smallredbook.utils.Constants;
//import com.zhy.http.okhttp.OkHttpUtils;
//import com.zhy.http.okhttp.callback.StringCallback;
//
//import java.util.Hashtable;
//import java.util.List;
//
//import okhttp3.Call;
//
//public class ceshi extends AppCompatActivity {
//    TextView tv_cs;
//    ImageView iv_pic;
//    Button bt;
//    String TAG = "联网信息";
//    VideoView vv ;
//    Button bt_play;
//    Button bt_stop;
//    ImageView iv_vv;
//    private MediaController mediaController;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ceshi);
//        tv_cs = findViewById(R.id.tv_cs);
//        iv_pic = findViewById(R.id.iv_pic);
//        bt = findViewById(R.id.bt);
//        tv_cs.setText("我的测试");
//        vv = findViewById(R.id.vv);
//        bt_play = findViewById(R.id.bt_play);
//        bt_stop = findViewById(R.id.bt_stop);
//        iv_vv = findViewById(R.id.iv_vv);
//        getNet();
//        init();
//        initView();
//
//
//        //ceshi();
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               // getNet();
//           //     initImage();
//                String uri= Constants.BASE_URL+"毛泽东.mp4";
//                Bitmap bitmap = getNetVideoBitmap(uri);
//                iv_pic.setImageBitmap(bitmap);//对应的ImageView赋值图片
//                iv_vv.setVisibility(View.VISIBLE);
//            }
//        });
//
//
//
//
//
//    }
//
//
//    public void ceshi() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                tv_cs.setText("run");
//                NoteDao noteDao= new NoteDao();
//                List<Note> notes = noteDao.find_all_note();
//for(int i =0;i<notes.size();i++)
//                Log.i("测试结果","数据"+notes.get(i).getNote_id()+notes.get(i).getNote_time());
//
//            }
//        }).start();
//
//    }
//    private void getNet() {
////        String url="https://www.csdn.net/";
//        final String url= Constants.BASE_URL+"activity1.jpg";
//        OkHttpUtils.get().url(url)
//                .addParams("username","hyman")
//                .addParams("password","123")
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//                Log.d(TAG,"联网失败"+e.getMessage());
//            }
//
//            @Override
//
//            public void onResponse(String response, int id) {
//
//                Log.d(TAG,"联网成功"+response);
////         iv_pic.setImageBitmap(getURLimage(url));
//                //processData(response);
//                Glide.with(ceshi.this).load(url).into(iv_pic);
//
//            }
//
//        });
//
//    }
//
//    private void initView() {
//
//        bt_play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                vv.start();
//            }
//        });
//        bt_stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vv.pause();
//            }
//        });
//    }
//
//    private void init() {
//
//        mediaController = new MediaController(this);
//        String uri= Constants.BASE_URL+"毛泽东.mp4";
//
//        // String uri = "android.resource://" + getPackageName() + "/" + R.raw.aas;
//        vv.setVideoURI(Uri.parse(uri));
//        vv.setMediaController(mediaController);
//        mediaController.setMediaPlayer(vv);
//        vv.requestFocus();
//
//        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                mediaPlayer.start();
//                mediaPlayer.setLooping(true);
//            }
//        });
//    }
//
//
//
//    public static Bitmap getNetVideoBitmap(String videoUrl) {
//        Bitmap bitmap = null;
//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//
//            retriever.setDataSource(videoUrl,new Hashtable<String, String>());
//            //获得第一帧图片
//           bitmap = retriever.getFrameAtTime();
//
//        return bitmap;
//    }
//
//
//
//}
