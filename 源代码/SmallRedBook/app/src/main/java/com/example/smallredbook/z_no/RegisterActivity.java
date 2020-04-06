package com.example.smallredbook.z_no;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smallredbook.R;
import com.example.smallredbook.app.MainActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText et_psw, et_user_name;

    private Button btn_register;
    private String userName, psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jiade);
        init();
    }
    private void init() {

        btn_register = (Button) findViewById(R.id.btn_register);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);

        btn_register.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_register:
                //获取输入在相应控件中的字符串
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
               /* UserBean userBean=new UserBean();
                userBean.setUser_id(userName);
                userBean.setPassword(psw);
                System.out.println(userBean.getUser_id());
                System.out.println(userBean.getPassword());
                DBUtils dbUtils=new DBUtils(getBaseContext());
                boolean flag=dbUtils.register(userBean);
                if(flag){
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    RegisterActivity.this.finish();
                }else {
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }*/
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(RegisterActivity.this, "请输入用户名",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码",
                            Toast.LENGTH_SHORT).show();
                    return;
                } /*else if (isExistUserName(userName)) {
                    Toast.makeText(RegisterActivity.this, "此账户名已经存在",
                            Toast.LENGTH_SHORT).show();
                    return;
                } */else {
                    Toast.makeText(RegisterActivity.this, "注册成功",
                            Toast.LENGTH_SHORT).show();
                    //把用户名和密码保存到SharedPreferences中
                    SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();//获取编辑器
                    //以用户名为key,密码为value保存到SharedPreferences中
                    editor.putString("id",userName);
                    editor.putString("password",psw);
                    editor.commit();//提交修改
                    //注册成功后把用户名传递到LoginActivity.java中
                   /* Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);*/
                    Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    //RegisterActivity.this.finish();
                }

                break;
        }
    }
    /**
     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     */
    private boolean isExistUserName(String userName) {
        boolean has_userName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(spPsw)) {
            has_userName = true;
        }
        return has_userName;
    }
    /**
     * 保存用户名和密码到SharedPreferences中
     */
    private void saveRegisterInfo(String userName, String psw) {

        //loginInfo表示文件名
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        //以用户名为key,密码为value保存到SharedPreferences中
        editor.putString("id",userName);
        editor.putString("password",psw);
        editor.commit();//提交修改
    }
}

