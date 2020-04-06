package com.example.smallredbook.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.smallredbook.R;
import com.example.smallredbook.base.BaseFragment;
import com.example.smallredbook.type.TypeFragment;
import com.example.smallredbook.home.HomeFragment;
import com.example.smallredbook.follow.FollowFragment;
import com.example.smallredbook.publish.PublishFragment;
import com.example.smallredbook.user.UserFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  BottomNavigationBar bottomNavigationBar;
    //装多个fragment实例的集合
    private ArrayList<BaseFragment> fragments;
    private int position_main = 0;
    Fragment tempFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();




        initFragment();
        bottomNavigationBar= findViewById(R.id.BNB_main);
        Intent intent = getIntent();
        String get_position_main = intent.getStringExtra("fragment_id");
        String search_note = intent.getStringExtra("search_note");
        setBottomNavigationBar();       //设置底边导航栏并添加相应的点击事件
        if (get_position_main!=null)
        {
            position_main = Integer.parseInt(get_position_main);
            bottomNavigationBar.selectTab(Integer.parseInt(get_position_main));
        }
        BaseFragment baseFragment_start = getFragment(position_main);
        switchFragment(tempFragment,baseFragment_start);

        if (get_position_main!=null) {
            Bundle bundle = new Bundle();
            bundle.putString("search_note", search_note);//这里的values就是我们要传的值
            fragments.get(1).setArguments(bundle);
        }
    }

    //改变状态栏的字体颜色
    protected void setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));//设置状态栏颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        }
    };
    /*
     * 设置底边导航栏并添加相应的点击事件
     * */
    public void  setBottomNavigationBar(){
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED) // 设置mode
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)  // 背景样式
//                .setBarBackgroundColor("#2FA8E1") // 背景颜色
                .setBarBackgroundColor("#000000") // 背景颜色
                .setInActiveColor("#929292") // 未选中状态颜色
                .setActiveColor("#ffffff") // 选中状态颜色
                .addItem(new BottomNavigationItem(R.drawable.home,"主页"))
                .addItem(new BottomNavigationItem(R.drawable.follow,"分类"))
                .addItem(new BottomNavigationItem(R.drawable.publish,"发表"))
                .addItem(new BottomNavigationItem(R.drawable.message,"关注"))
                .addItem(new BottomNavigationItem(R.drawable.user,"我"))
                .setFirstSelectedPosition(0) //设置默认选中位置
                .initialise() ; // 提交初始化（完成配置）
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch(position){
                    case 0:
                         position_main =0;
                        break;
                    case 1:
                        position_main =1;
                        break;
                    case 2:
                        position_main =2;
                        break;
                    case 3:
                        position_main =3;
                        break;
                    case 4:
                        position_main =4;
                        break;
                        default: position_main=0;
                }
                BaseFragment baseFragment = getFragment(position_main);
                switchFragment(tempFragment,baseFragment);
            }

            @Override
            public void onTabUnselected(int position) {

            }
            @Override
            public void onTabReselected(int position) {

            }
        });


    }

    /*
    *将各个fragment添加到list中，方便调用
    * */
    private  void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new PublishFragment());
        fragments.add(new FollowFragment());
        fragments.add(new UserFragment());
    }

    /*
    * 根据位置返回相应的fragment
    * */
    private  BaseFragment getFragment(int position){
        if(fragments!=null&&fragments.size()>0)
        {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /*
    * 切换fragment ，若与上次fragment相同，则不响应
    * */
    private void switchFragment(Fragment fragment,BaseFragment nextFragment){
        if(tempFragment!=nextFragment){
            tempFragment = nextFragment;
        if(nextFragment!=null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(!nextFragment.isAdded()){
                if(fragment!=null){
                    transaction.hide(fragment);
                }
                transaction.add(R.id.FL_main,nextFragment).commit();
            }
            else {
                if(fragment!=null){
                    transaction.hide(fragment);
                }
                transaction.show(nextFragment).commit();
            }
        }
    }
    }

}
