package com.example.smallredbook.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.smallredbook.R;


import com.example.smallredbook.z_no.PhotoPopupWindow;
import com.example.smallredbook.app.login;
import com.example.smallredbook.publish.publish;
import com.example.smallredbook.base.BaseFragment;
import com.example.smallredbook.base.TabFragment;
import com.example.smallredbook.model.Note;
import com.example.smallredbook.model.NoteDao;
import com.example.smallredbook.utils.Constants;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/*
* 用户的Fragment
* */
public class UserFragment extends BaseFragment {
    private ViewPager pager;
    private UserAdapter userAdapter;
    private List<TabFragment> fragmentList;
    private TabLayout tabLayout;
    private List<String> mTitle;
    private TabFragment fragment1,fragment2,fragment3;
    private  String [] title={"笔记","收藏","赞过"};
    private ImageView cancel;
    private TextView edit;
    private TextView pwd;
    private TextView TV_share;
    private ImageView icon;
    private List<Note>  notes,notes2,notes3;
    PhotoPopupWindow mphotoPopupWindow;
    private int user_id = Constants.user_id;
    private static final int request_image_get=0 ;
    private static final int request_image_capture=1;
    private static final int request_image_cutting=2;
    private static final  String IMAGE_FILE_NAME="icon.jpg";
    private TextView tv_user_name;



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

    }

    @Override
    public View initView() {                           //初始化fragment要加载的页面，并添加相应的监听事件，返回该页面
        View  view = View.inflate(mContext, R.layout.fragment_user,null);

        pager = view.findViewById(R.id.VP_content_2);
        tabLayout = view.findViewById(R.id.TL_top_bar);
        edit= view.findViewById(R.id.TV_edit);
        pwd=view.findViewById(R.id.TV_editpwd);
        cancel=view.findViewById(R.id.IV_set);
        icon=view.findViewById(R.id.main_icon);
        TV_share=view.findViewById(R.id.TV_share);
        tv_user_name = view.findViewById(R.id.tv_user_name);
        tv_user_name.setText(Constants.user_name);
        Glide.with(mContext).load(Constants.User_URL+Constants.user_head).into(icon);
        init_note();

        pwd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ChangepwdActivity.class);
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("提示：").setMessage("确定要退出吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext, login.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
                builder.show();

            }
        });
        TV_share.setOnClickListener(new View.OnClickListener( ){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, publish.class);//注意要改成是发表的class
                startActivity(intent);

            }
        });
//                icon.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //相册选择
//                        mphotoPopupWindow = new PhotoPopupWindow((Activity) mContext, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
//                                } else {
//                                    mphotoPopupWindow.dismiss();
//                                    Intent intent = new Intent(Intent.ACTION_PICK);
//                                    intent.setType("image/*");
//                                    startActivityForResult(intent, request_image_get);
//                                }
//                            }
//                        }, new View.OnClickListener() {
//                            //拍照
//                            @Override
//                            public void onClick(View v) {
//                                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
//                                        || ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 300);
//                                } else {
//                                    mphotoPopupWindow.dismiss();
//                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//
//                                    imageCapture();
//                                }
//
//                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
//                                startActivityForResult(intent, request_image_capture);
//
//                            }
//                        });
//                        View rootView = LayoutInflater.from(mContext).inflate(R.layout.fragment_user, null);
//                        mphotoPopupWindow.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//                    }
//                });

        return view;
    }

    private  void imageCapture(){
        Intent intent;
        Uri pictrueUri;
        File pictureFile =new File(Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME);
            intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictrueUri=Uri.fromFile(pictureFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT,pictrueUri);
        startActivityForResult(intent,request_image_capture);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case request_image_get:
                try{
                    startSmallPhoneZoom(data.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case request_image_capture:
                File temp=new File(Environment.getExternalStorageDirectory()+"/"+IMAGE_FILE_NAME);
                startSmallPhoneZoom(Uri.fromFile(temp));
                break;
            case request_image_cutting:
                if(data!=null){
                    setPictToView(data);
                }
                break;
        }
    }

    public void startSmallPhoneZoom(Uri uri){
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        intent.putExtra("crop","true");
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",300);
        intent.putExtra("outputY",300);
        intent.putExtra("scale",true);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,request_image_cutting);

    }

    private void setPictToView(Intent data){
        Bundle extras = data.getExtras();
        if(extras!=null){
            Bitmap photo=extras.getParcelable("data");//获取内存中的bitmap
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                String storage=Environment.getExternalStorageDirectory().getPath();
                File dirfile=new File(storage+"/smallIcon");
                if(!dirfile.exists()){
                    if(dirfile.mkdirs()){
                        Log.e("TAG","文件创建失败");
                    }else{
                        Log.e("TAG","文件创建成功");
                    }
                }
                //保存图片
                File file=new File(dirfile,System.currentTimeMillis()+".jpg");
                FileOutputStream outputStream=null;
                try{
                    outputStream=new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            icon.setImageBitmap(photo);
        }
    }


    @Override
    public void initData() {                    //加载网络请求的数据，若无数据要求可不用重写
        Log.i("用户页面","初始化页面");
        super.initData();
        fragmentList = new ArrayList<>();
        mTitle = new ArrayList<>();
        for(int i= 0;i<title.length;i++)
        {
            mTitle.add(title[i]);

        }
        fragmentList.add(new TabFragment(notes,31));
        fragmentList.add(new TabFragment(notes2,32));
        fragmentList.add(new TabFragment(notes3,33));
        userAdapter = new UserAdapter(getActivity().getSupportFragmentManager(),fragmentList,mTitle);
        pager.setAdapter(userAdapter);
        tabLayout.setTabRippleColor(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorWhite)));
        tabLayout.setupWithViewPager(pager);

    }





    public void initData2() {                    //加载网络请求的数据，若无数据要求可不用重写
        super.initData();
        fragmentList = new ArrayList<>();
        mTitle = new ArrayList<>();
        for(int i= 0;i<title.length;i++)
        {
            mTitle.add(title[i]);
            fragmentList.add(new TabFragment(notes));
//            待修改


        }
        userAdapter = new UserAdapter(getActivity().getSupportFragmentManager(),fragmentList,mTitle);
        pager.setAdapter(userAdapter);
        tabLayout.setTabRippleColor(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorWhite)));
        tabLayout.setupWithViewPager(pager);


    }

    private void init_note() {              //获取笔记信息
        new Thread( ){            //创建一个子线程
            public void run() {
                Log.i("测试结果", "测试2");
                Message msg = new Message();
                msg.obj = "子线程111";
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                notes = noteDao.find_my_note_by_type(user_id,0);           //获取自己的笔记
//                notes = noteDao.find_my_note_by_type(user_id,5);
                notes2 = noteDao.find_my_note_by_type(user_id,1);      //获取自己收藏的笔记
                notes3 = noteDao.find_my_note_by_type(user_id,2);      //获取点赞的笔记
                   Log.i("测试结果", "home笔记数量" + notes.size());

                mHander.sendMessage(msg);
            };
        }.start();

    }

    Handler mHander = new Handler(){             //创建一个主线程监听，当子线程init_note（）执行完毕后，执行下面initData（）方法；
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    Log.i("用户页面获取子线程","初始化页面"+msg.obj);
                    initData();
                    break;
                default:break;
            }

        }
    };






}



