package com.example.smallredbook.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

@SuppressLint("AppCompatCustomView")
public class HeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {
    public HeaderView(Context context) {
        super(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
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
