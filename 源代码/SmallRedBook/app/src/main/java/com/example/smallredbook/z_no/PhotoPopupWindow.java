package com.example.smallredbook.z_no;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.smallredbook.R;

public class PhotoPopupWindow extends PopupWindow {
    private View mview;
    private Context mcontext;
    private View.OnClickListener mSelectListener;
    private View.OnClickListener mCaptureistener;
    public PhotoPopupWindow(Activity context,View.OnClickListener SelectListener,View.OnClickListener Captureistener){
        super(context);
        this.mcontext=context;
        this.mSelectListener=SelectListener;
        this.mCaptureistener=Captureistener;
        init();
    }

    public PhotoPopupWindow(Context mContext, View.OnClickListener onClickListener) {
    }

    private  void init(){
        LayoutInflater inflater=(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mview=inflater.inflate(R.layout.pop_item,null);
        Button btn_camera=(Button)mview.findViewById(R.id.icon_btn_camera);
        Button btn_select=(Button)mview.findViewById(R.id.icon_btn_select);
        Button btn_cancel=(Button)mview.findViewById(R.id.icon_btn_cancel);
        btn_select.setOnClickListener(mSelectListener);
        btn_camera.setOnClickListener(mCaptureistener);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //导入布局
        this.setContentView(mview);

        //设置可触
        this.setFocusable(true);
        ColorDrawable dw=new ColorDrawable(0x0000000);
        this.setBackgroundDrawable(dw);

        mview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int heigth=mview.findViewById(R.id.ll_pop).getTop();
                int y=(int)event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<heigth){
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
