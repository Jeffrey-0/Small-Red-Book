package com.example.smallredbook.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smallredbook.R;
import com.example.smallredbook.type.TypeFragment;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
private EditText et_text;
private ImageView iv_back;
private ImageView iv_delete;
private TextView tv_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setStatusBar();//设置状态栏
        et_text = (EditText) findViewById(R.id.ET_search);
        iv_back = findViewById(R.id.iv_black);
        iv_delete = findViewById(R.id.iv_delete);
        tv_search = findViewById(R.id.tv_search);

        iv_back.setOnClickListener(this);
        iv_delete.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        showSoftInputFromWindow(this,et_text);
}

    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }
    //改变状态栏的字体颜色
    protected void setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));//设置状态栏颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_black:
                finish();
                break;
            case R.id.iv_delete:
                Toast.makeText(this, "删除历史记录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search:
                String search_note = et_text.getText().toString();
                Toast.makeText(this, search_note, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("fragment_id","1");
                intent.putExtra("search_note",search_note);
                startActivity(intent);
                break;

        }
    }
}

