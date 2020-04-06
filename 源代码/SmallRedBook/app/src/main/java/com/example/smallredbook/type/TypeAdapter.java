package com.example.smallredbook.type;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.smallredbook.base.TabFragment;

import java.util.List;

public class TypeAdapter extends FragmentPagerAdapter {

    private List<TabFragment> mFragmentList;//各导航的Fragment
    private List<String> mTitle; //导航的标题
    public TypeAdapter(FragmentManager fm, List<TabFragment> fragments, List<String> titles) {
        super(fm);
        mFragmentList = fragments;
        mTitle = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }



}
