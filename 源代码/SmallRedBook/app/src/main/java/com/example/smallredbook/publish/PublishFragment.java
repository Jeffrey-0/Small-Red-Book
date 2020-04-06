package com.example.smallredbook.publish;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.smallredbook.base.BaseFragment;

/*
* 发表的Fragment
* */
public class PublishFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {                           //初始化fragment要加载的页面，并添加相应的监听事件，返回该页面
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(mContext,publish.class);
        startActivity(intent);
    }

    @Override
    public void initData() {                    //加载网络请求的数据，若无数据要求可不用重写
        super.initData();
        textView.setText("发表");
    }
}
