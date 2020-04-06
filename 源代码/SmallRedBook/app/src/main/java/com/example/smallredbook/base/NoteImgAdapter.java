package com.example.smallredbook.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.smallredbook.utils.Constants;

import java.util.List;

public class NoteImgAdapter extends PagerAdapter {
    private Context mContext;
    private List<String>  lists;

    public NoteImgAdapter(Context context,List<String > lists){
        mContext = context;
        this.lists = lists;

    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        String img_url = Constants.Note_URL + lists.get(position);
        Glide.with(mContext).load(img_url).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       // super.destroyItem(container, position, object);
container.removeView((View) object);
    }
}
