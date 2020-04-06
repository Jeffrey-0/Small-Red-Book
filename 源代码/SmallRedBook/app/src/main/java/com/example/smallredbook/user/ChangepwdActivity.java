package com.example.smallredbook.user;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smallredbook.R;
import com.example.smallredbook.model.UserDao;
import com.example.smallredbook.utils.Constants;


public class ChangepwdActivity extends AppCompatActivity {

    private EditText et_original_psw, et_new_psw,et_new_psw2;
    private String originalPsw, newPsw,newPsw2;
    private String userName;
    private  Button save ,cancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_changepwd);
        setStatusBar();
        init();

    }
    /**
     * 获取界面控件并处理相关控件的点击事件
     */
    private void init() {

        et_original_psw = (EditText) findViewById(R.id.et_now);
        et_new_psw = (EditText) findViewById(R.id.et_new);
        et_new_psw2 = (EditText) findViewById(R.id.et_new_password);
        save = (Button) findViewById(R.id.btn_update_password);
        cancel = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangepwdActivity.this.finish();
            }
        });

        //保存按钮的点击事件
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(originalPsw)) {
                    Toast.makeText(ChangepwdActivity.this, "请输入原始密码",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if (!originalPsw.equals(readPsw())) {
                    Toast.makeText(ChangepwdActivity.this, "输入的密码与原始密码不一致",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if ((newPsw).equals(readPsw())){
                    Toast.makeText(ChangepwdActivity.this, "输入的新密码与原始密码不能一致",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(newPsw)) {
                    Toast.makeText(ChangepwdActivity.this, "请输入新密码",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(newPsw2)) {
                    Toast.makeText(ChangepwdActivity.this, "请再次输入新密码",
                            Toast.LENGTH_SHORT).show();
                    return;}
                else if (!(newPsw).equals(newPsw2)) {
                    Toast.makeText(ChangepwdActivity.this, "两次新密码不一致",
                            Toast.LENGTH_SHORT).show();
                    return;}
                else {
                    Toast.makeText(ChangepwdActivity.this, "新密码设置成功",
                            Toast.LENGTH_SHORT).show();
                    //修改登录成功时保存在SharedPreferences中的密码
                    modifyPsw(newPsw);
                   /* Intent intent = new Intent(ChangepwdActivity.this,
                            login.class);
                    startActivity(intent);*/

                    ChangepwdActivity.this.finish();   //关闭本界面

                    //保存在数据库？？？？
                }
            }
        });
    }
    /**
     * 获取控件上的字符串
     */
    private void getEditString() {
        originalPsw = et_original_psw.getText().toString().trim();
        newPsw = et_new_psw.getText().toString().trim();
        newPsw2 = et_new_psw2.getText().toString().trim();
    }
    /**
     * 修改登录成功时保存在SharedPreferences中的密码
     */
    private void modifyPsw(final String newPsw) {

//        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();  //获取编辑器
//        editor.putString("password", newPsw);             //保存新密码
//        editor.commit();                                    //提交修改
Constants.password = newPsw;
        new Thread( ){            //创建一个子线程
            public void run() {
                Log.i("测试结果", "测试2");
                Message msg = new Message();
                msg.obj = "子线程111";
                UserDao userDao = new UserDao();
//                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                userDao.update_password(newPsw);


            };
        }.start();


    }
    /**
     * 从SharedPreferences中读取原始密码
     */
    private String readPsw() {
//        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
//        String spPsw = sp.getString("password", "");
//        return spPsw;

        return Constants.password;
    }
    //改变状态栏的字体颜色
    protected void setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));//设置状态栏颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        }
    };


}





