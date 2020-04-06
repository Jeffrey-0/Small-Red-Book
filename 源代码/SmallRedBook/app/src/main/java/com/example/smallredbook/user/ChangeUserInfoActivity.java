package com.example.smallredbook.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smallredbook.R;

public class ChangeUserInfoActivity extends AppCompatActivity {

    private TextView tv_back;
    private TextView tv_save;
    private String title, content;
    private int flag;  //flag为1时表示修改昵称，为2时表示修改签名，1用户名，3电话，4 生日，5，地址，6，个性签名
    private EditText et_content;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_user_info);
            setStatusBar();
        //设置此界面为竖屏
        init();
    }
    private void init() {
        //从个人资料界面传递过来的标题和内容
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag", 0);

        tv_back = (TextView) findViewById(R.id.TV_cancel);
        tv_save = (TextView) findViewById(R.id.TV_save);
        tv_back.setVisibility(View.VISIBLE);
        tv_save.setVisibility(View.VISIBLE);
        et_content = (EditText) findViewById(R.id.et_content);

        if (!TextUtils.isEmpty(content)) {
            et_content.setText(content);
            et_content.setSelection(content.length());
        }
        contentListener();
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeUserInfoActivity.this.finish();
            }
        });

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                String etContent = et_content.getText().toString().trim();
                switch (flag) {
                    /*case 1:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("nickName", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功",
                                    Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        } else {
                            Toast.makeText(ChangeUserInfoActivity.this,
                                    "昵称不能为空",Toast.LENGTH_SHORT).show();
                        }
                        break;*/
                   case 6:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("signature", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功",
                                    Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        } else {
                            Toast.makeText(ChangeUserInfoActivity.this,
                                    "签名不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 1:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("user_name", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功",
                                    Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        } else {
                            Toast.makeText(ChangeUserInfoActivity.this,
                                    "用户名不能为空",Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 3:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("phone", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功",
                                    Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        } else {
                            Toast.makeText(ChangeUserInfoActivity.this,
                                    "电话不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 4:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("birthday", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功",
                                    Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        } else {
                            Toast.makeText(ChangeUserInfoActivity.this,
                                    "生日不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 5:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("address", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功",
                                    Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        } else {
                            Toast.makeText(ChangeUserInfoActivity.this,
                                    "地址不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;


                }
            }
        });
    }
    /**
     * 监听个人资料修改界面输入的文字
     */
    private void contentListener() {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Editable editable = et_content.getText();
                int len = editable.length();//输入的文本的长度

                switch (flag) {
                    case 1:   //昵称
                        //昵称限制最多8个文字，超过8个需要截取掉多余的文字
                        if (len > 8) {
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            //截取新字符串
                            String newStr = str.substring(0, 8);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            //新字符串的长度
                            int newLen = editable.length();
                            //旧光标位置超过新字符串的长度
                            if (selEndIndex > newLen) {
                                selEndIndex = editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable, selEndIndex);
                        }
                        break;
                    case 2:   //签名
                        //签名最多是16个文字，超过16个需要截取掉多余的文字
                        if (len > 16) {
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            //截取新字符串
                            String newStr = str.substring(0, 16);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            //新字符串的长度
                            int newLen = editable.length();
                            //旧光标位置超过新字符串的长度
                            if (selEndIndex > newLen) {
                                selEndIndex = editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable, selEndIndex);
                        }
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
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


