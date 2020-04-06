package com.example.smallredbook.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smallredbook.R;
import com.example.smallredbook.model.UserDao;

public class login extends AppCompatActivity {

    private static final String TAG = "login";
    private EditText name;
    private EditText password;
    private Button login_btn;
    private Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        register_btn = findViewById(R.id.register_btn);
        loginButton();
        register();

    }


    public void loginButton() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String n = name.getText().toString().trim();
                        String psw = password.getText().toString().trim();
                        if (n.equals("") || psw.equals("")) {
                            Looper.prepare();
                            Toast toast = Toast.makeText(login.this, "输入不能为空", Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                        UserDao ud = new UserDao();
                        Boolean result = ud.logining(n, psw);
                        if (!result) {
                            Looper.prepare();
                            Toast toast = Toast.makeText(login.this, "用户名不存在或密码错误", Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast toast = Toast.makeText(login.this, "登录成功", Toast.LENGTH_SHORT);
                            toast.show();
//                            Constants.user_id = 111;
//                            Intent intent = new Intent(login.this, MainActivity.class);
                            Intent intent = new Intent(login.this,MainActivity.class);
                            startActivity(intent);
                            Looper.loop();
                        }
                    }
                }).start();
            }
        });
    }

    public void register() {
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }
    //改变状态栏的字体颜色
    protected void setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));//设置状态栏颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        }
    };
}
