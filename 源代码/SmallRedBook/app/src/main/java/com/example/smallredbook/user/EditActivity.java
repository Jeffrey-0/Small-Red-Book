package com.example.smallredbook.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smallredbook.R;
import com.example.smallredbook.model.UserBean;
import com.example.smallredbook.model.UserDao;
import com.example.smallredbook.utils.Constants;
//import com.example.smallredbook.utils.DBUtils;
//import com.example.smallredbook.utils.UserBean;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{
    private Button save,cancel;

    private TextView  tv_signature, tv_user_name, tv_sex,tv_address,tv_birthday,tv_phone;
    private RelativeLayout rl_nickName, rl_sex, rl_signature,rl_address,rl_phone,rl_user_name;
    private String id;
    private static final int CHANGE_BIRTHDAY = 4;   //修改昵称的自定义常量
    private static final int CHANGE_SIGNATURE = 6; //修改签名的自定义常量
    private static final int CHANGE_ADDRESS = 5; //修改地址的自定义常量
    private static final int CHANGE_PHONE = 3; //修改地址的自定义常量
    private static final int CHANGE_USERNAME= 1; //修改地址的自定义常量
    private    boolean  pass = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(R.layout.activity_edit);
          init();
        //  initData();
        setValue();
         setListener();
    }
    private void init(){


        rl_user_name = findViewById(R.id.rl_account);
        rl_nickName = (RelativeLayout) findViewById(R.id.rl_birthday);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_signature = (RelativeLayout) findViewById(R.id.rl_signature);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_signature = (TextView) findViewById(R.id.tv_signature);

        tv_address = findViewById(R.id.tv_address);
        rl_address = findViewById(R.id.rl_address);
        tv_phone = findViewById(R.id.tv_phone);
        rl_phone = findViewById(R.id.rl_phone);


        save=(Button)findViewById(R.id.save);
        cancel=(Button)findViewById(R.id.cancel);

    }
    /**
     * 获取数据
     */
    private void initData() {
        //从SharedPreferences中获取登录时的用户名
//        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
//        id = sp.getString("id", "");
        UserBean bean = null;
//
//        bean = DBUtils.getInstance(this).getUserInfo(id);
//        //首先判断一下数据库是否有数据
//        if (bean == null) {
//            bean = new UserBean();
//            bean.setUser_id(Integer.parseInt(id));
//           // bean.setUser_id("lhj");
//            bean.setNickName("问答精灵");
//            bean.setSex("男");
//            bean.setSignature("传智播客问答精灵");
//
//
//            //保存用户信息到数据库
//            DBUtils.getInstance(this).saveUserInfo(bean);
//        }
//        else{
//                //获取数据库的数据
//        }
//        setValue(bean);
    }

      private void setValue() {
//        tv_birthday.setText(bean.getNickName());
//        tv_user_name.setText(bean.getUser_id());
//        tv_sex.setText(bean.getSex());
//        tv_signature.setText(bean.getSignature());
        tv_user_name.setText(Constants.user_name);
        tv_address.setText(Constants.user_address);
        tv_phone.setText(Constants.phone);
        tv_birthday.setText(Constants.birthday);
        tv_sex.setText(Constants.sex);
        tv_signature.setText(Constants.singnature);


    }
    /**
     * 设置控件的点击监听事件
     */
      private void setListener() {

        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
        rl_address.setOnClickListener(this);
        rl_phone.setOnClickListener(this);

rl_user_name.setOnClickListener(this);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }
    /**
     * 控件的点击事件
     */
   @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.rl_account: //昵称的点击事件
                String name = tv_user_name.getText().toString().trim(); //获取昵称控件上的数据
                Bundle bdName = new Bundle();
                bdName.putString("content", name);                //传递界面上的昵称数据
                bdName.putString("title", "用户名");
                bdName.putInt("flag", 1);                           //flag传递1时表示是修改昵称
                //跳转到个人资料修改界面
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_USERNAME , bdName);
                break;
            case R.id.rl_birthday: //昵称的点击事件
                String birthd = tv_birthday.getText().toString().trim(); //获取昵称控件上的数据
                Bundle bdBirthd = new Bundle();
                bdBirthd.putString("content", birthd);                //传递界面上的昵称数据
                bdBirthd.putString("title", "生日");
                bdBirthd.putInt("flag", 4);                           //flag传递1时表示是修改昵称
                //跳转到个人资料修改界面
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_BIRTHDAY , bdBirthd);
                break;
             case R.id.rl_sex:       //性别的点击事件
                String sex = tv_sex.getText().toString(); //获取性别控件上的数据
                sexDialog(sex);
                break;
            case R.id.rl_signature:  //签名的点击事件
                String signature = tv_signature.getText().toString().trim(); //获取签名控件上的数据
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content", signature);            //传递界面上的签名数据
                bdSignature.putString("title", "签名");
                bdSignature.putInt("flag", 6);                            //flag传递2时表示是修改签名
                //跳转到个人资料修改界面
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_SIGNATURE, bdSignature);
                break;

            case R.id.rl_address: //昵称的点击事件
                String address = tv_address.getText().toString().trim(); //获取昵称控件上的数据
                Bundle bdAddress = new Bundle();
                bdAddress.putString("content",address);                //传递界面上的昵称数据
                bdAddress.putString("title", "地址");
                bdAddress.putInt("flag", 5);                           //flag传递1时表示是修改昵称
                //跳转到个人资料修改界面

                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_ADDRESS, bdAddress);
                break;
            case R.id.rl_phone: //昵称的点击事件
                String phone = tv_phone.getText().toString().trim(); //获取昵称控件上的数据
                Bundle bdPhone = new Bundle();
                bdPhone.putString("content", phone);                //传递界面上的昵称数据
                bdPhone.putString("title", "电话");
                bdPhone.putInt("flag", 3);                           //flag传递1时表示是修改昵称
                //跳转到个人资料修改界面
                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_PHONE, bdPhone);
                break;
            case  R.id.save:
                EditActivity.this.finish();
            case  R.id.cancel:
                EditActivity.this.finish();
            default:
                break;
        }
    }

    private void sexDialog(String sex){
        int sexFlag=0;
        if("男".equals(sex)){
            sexFlag=0;
        }else if("女".equals(sex)){
            sexFlag=1;
        }
        final String items[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("性别"); //设置标题
        final int finalSexFlag = sexFlag;
        builder.setSingleChoiceItems(items,sexFlag,new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //第二个参数which是默认选中的哪个项
                Toast.makeText(EditActivity.this,items[which],
                        Toast.LENGTH_SHORT).show();
                setSex(items[which]);
                UserDao userDao2 = new UserDao();
                userDao2.update_sex(items[finalSexFlag]);

            }
        });
        builder.create().show();
    }
    /**
     * 更新界面上的性别数据
     */
    private void setSex(final String sex){
        tv_sex.setText(sex);
        //更新数据库中的性别字段
//        DBUtils.getInstance(EditActivity.this).updateUserInfo("sex",sex,
//                id);
//        UserDao userDao = new UserDao();

        new Thread( ){            //创建一个子线程
            public void run() {
                Log.i("测试结果", "测试2");
//                Message msg = new Message();
//                msg.obj = "子线程111";
                UserDao userDao = new UserDao();
//                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                userDao.update_sex(sex);
                Constants.sex=sex;


            };
        }.start();



    }

    /**
     * 设置性别的弹出框
     */

    /**
     * 获取回传数据时需使用的跳转方法，第一个参数to表示需要跳转到的界面，
     * 第二个参数requestCode表示一个请求码，第三个参数b表示跳转时传递的数据
     */
    public void enterActivityForResult(Class<?> to, int requestCode, Bundle b) {
        Intent i = new Intent(this, to);
        i.putExtras(b);
        startActivityForResult(i, requestCode);
    }
    /**
     * 回传数据
     */
    private String new_info; //最新数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UserDao userDao = new UserDao();
        switch (requestCode) {

            case CHANGE_USERNAME:  //个人资料修改界面回传过来的昵称数据
                if (data != null) {
                    new_info = data.getStringExtra("user_name");
                    if (TextUtils.isEmpty(new_info)) {
                        return;
                    }
                    tv_user_name.setText(new_info);
                    //更新数据库中的昵称字段（保存在数据库)
//                    DBUtils.getInstance(EditActivity.this).updateUserInfo(
//                            "nickName", new_info, id);
                    new Thread( ){            //创建一个子线程
                        public void run() {
                            Log.i("测试结果", "测试2");
                            Message msg = new Message();
                            msg.obj = "子线程111";
                            UserDao userDao = new UserDao();
//                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                            userDao.update_user_name(new_info);
                            Constants.user_name=new_info;


                        };
                    }.start();


                }
                break;


            case CHANGE_SIGNATURE: //个人资料修改界面回传过来的签名数据（保存在数据库)
                if (data != null) {
                    new_info = data.getStringExtra("signature");
                    if (TextUtils.isEmpty(new_info)) {
                        return;
                    }
                    tv_signature.setText(new_info);

                    new Thread( ){            //创建一个子线程
                        public void run() {
                            Log.i("测试结果", "测试2");
                            Message msg = new Message();
                            msg.obj = "子线程111";
                            UserDao userDao = new UserDao();
//                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                            userDao.update_describe(new_info);
                            Constants.singnature = new_info;

                        };
                    }.start();


//                    //更新数据库中的签名字段(保存在数据库)
//                    DBUtils.getInstance(EditActivity.this).updateUserInfo(
//                            "signature", new_info, id);
                }
                    break;
                    case CHANGE_ADDRESS: //个人资料修改界面回传过来的签名数据（保存在数据库)
                        if (data != null) {
                            new_info = data.getStringExtra("address");
                            if (TextUtils.isEmpty(new_info)) {
                                return;
                            }
                            tv_address.setText(new_info);

                            new Thread( ){            //创建一个子线程
                                public void run() {
                                    Log.i("测试结果", "测试2");
                                    Message msg = new Message();
                                    msg.obj = "子线程111";
                                    UserDao userDao = new UserDao();
//                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                                    userDao.update_address(new_info);
                        Constants.user_address = new_info;

                                };
                            }.start();

                            //更新数据库中的签名字段(保存在数据库)
//                    DBUtils.getInstance(EditActivity.this).updateUserInfo(
//                            "signature", new_info, id);
                        }
                        break;

                    case CHANGE_PHONE: //个人资料修改界面回传过来的签名数据（保存在数据库)
                        if (data != null) {
                            new_info = data.getStringExtra("phone");
                            if (TextUtils.isEmpty(new_info)) {
                                return;
                            }
                            tv_phone.setText(new_info);
                            new Thread( ){            //创建一个子线程
                                public void run() {
                                    Log.i("测试结果", "测试2");
                                    Message msg = new Message();
                                    msg.obj = "子线程111";
                                    UserDao userDao = new UserDao();
//                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                                    userDao.update_phone(new_info);
                                    Constants.phone = new_info;

                                };
                            }.start();

                            //更新数据库中的签名字段(保存在数据库)
//                            DBUtils.getInstance(EditActivity.this).updateUserInfo(
//                                    "signature", new_info, id);
                        }
                        break;

                }
        }
    //改变状态栏的字体颜色
    protected void setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));//设置状态栏颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        }
    };

    }
