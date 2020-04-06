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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smallredbook.R;
import com.example.smallredbook.model.UserDao;

public class register extends AppCompatActivity {

    private static final String TAG = "register";
    private EditText user_name;
    private EditText et_password;
    private EditText et_phone;
    private EditText et_sex;
    private Button bt_submit_register;
    private ImageView iv_return;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setStatusBar();
        init();

        btRegister();
    }

    public void init() {
        user_name = findViewById(R.id.user_name);
        et_password = findViewById(R.id.et_password);
        et_phone = findViewById(R.id.et_phone);
        et_sex = findViewById(R.id.et_sex);
        bt_submit_register = findViewById(R.id.bt_submit_register);
        iv_return = findViewById(R.id.iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void btRegister() {
        bt_submit_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String n = user_name.getText().toString().trim();
                        String psw = et_password.getText().toString().trim();
                        String p = et_phone.getText().toString().trim();
                        String s= et_sex.getText().toString().trim();
                        UserDao ud = new UserDao();
                        boolean result = ud.register(n, psw,p,s);
                        if (!result) {
                            Looper.prepare();
                            Toast toast = Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(register.this, login.class);
                            startActivity(intent);
                            Looper.loop();
                        }
                        Log.i(TAG, "register" + result);
                    }
                }).start();
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
