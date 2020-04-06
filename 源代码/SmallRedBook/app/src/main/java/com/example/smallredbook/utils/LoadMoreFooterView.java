package com.example.smallredbook.utils;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import android.annotation.SuppressLint;
import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by Administrator on 2017/3/17.
 */
@SuppressLint("AppCompatCustomView")
public class LoadMoreFooterView extends TextView implements SwipeTrigger, SwipeLoadMoreTrigger {
    public LoadMoreFooterView(Context context) {
        super(context);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadMore() {
        setText("正在加载");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (!b) {
            if (i <= -getHeight()) {
                setText("松开后加载");
            } else {
                setText("上拉加载更多");
            }
        } else {
            setText("4");
        }
    }

    @Override
    public void onRelease() {
        setText("5");
    }

    @Override
    public void onComplete() {
        setText("加载完成");
    }

    @Override
    public void onReset() {
        setText("");
    }
}

