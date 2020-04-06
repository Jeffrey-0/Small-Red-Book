package com.example.smallredbook.utils;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import android.annotation.SuppressLint;
import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

@SuppressLint("AppCompatCustomView")
public class RefreshHeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {
    public RefreshHeaderView(Context context) {
        super(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onRefresh() {
        setText("正在刷新");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (!b) {
            if (i >= getHeight()) {
                setText("松开后刷新");
            } else {
                setText("下拉刷新");
            }
        } else {
            setText("刷新完毕");
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        setText("刷新完成");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
