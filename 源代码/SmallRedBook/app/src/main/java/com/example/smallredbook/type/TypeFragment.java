package com.example.smallredbook.type;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.smallredbook.R;
import com.example.smallredbook.app.SearchActivity;
import com.example.smallredbook.base.BaseFragment;
import com.example.smallredbook.base.TabFragment;
import com.example.smallredbook.home.HomeAdapter;
import com.example.smallredbook.model.Note;
import com.example.smallredbook.model.NoteDao;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/*
* 关注的Fragment
* */
public class TypeFragment extends BaseFragment {
    private List<String> Menus = new ArrayList<>();
    private HorizontalScrollView hs_menus;
    private List<TextView> listmenus;
    private LinearLayout ll_menus;
    private TextView tv_search;
    private ViewPager vp_type;
    private int screenWitdth ;
    private TypeAdapter typeAdapter;
    private List<TabFragment> fragmentList;
    private List<Note>  notes,notes2,notes3,notes4,notes5,notes6,notes7,notes8,notes9,notes10,notes11,notes12;
    private List<Note>  notelist[];
    private int user_id = 111;
    String search_note ="";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View initView() {                           //初始化fragment要加载的页面，并添加相应的监听事件，返回该页面

        View view = View.inflate(mContext, R.layout.fragment_type,null);            //设置要加载的页面
        hs_menus = view.findViewById(R.id.hs_menus);
        ll_menus = view.findViewById(R.id.ll_menus);
        vp_type = view.findViewById(R.id.vp_type);
        tv_search = view.findViewById(R.id.tv_search);
        if(search_note!=null&&search_note!="")
            tv_search.setText(search_note);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
            }
        });
        //获得屏幕宽度
        screenWitdth = getResources().getDisplayMetrics().widthPixels;
        initMenus();
        addMenus();

        init_note();
        return view;                        //返回该页面
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
            search_note = getArguments().getString("search_note");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onCreateView(inflater, container, savedInstanceState);


    }

    private void addMenus() {
        for(int i = 0;i<Menus.size();i++){
            TextView textView = new TextView(mContext);
            textView.setText(Menus.get(i));
            textView.setTextSize(20);
            textView.setPadding(20,0,20,0);
            if(i==0){
                textView.setTextColor(Color.BLACK);
            }else
            {
                textView.setTextColor(0xFF929292);
            }
            textView.setTag(i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelectMenu(Integer.parseInt(view.getTag()+""));
                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,5,10,10);
            ll_menus.addView(textView,params);
            listmenus.add(textView);

        }
    }

    private void setSelectMenu(int parseInt) {
        vp_type.setCurrentItem(parseInt);

        for(int i = 0;i<listmenus.size();i++)
        {
            if(i==parseInt){
                listmenus.get(i).setTextColor(Color.BLACK);
                //选中居中设置
                int left=listmenus.get(i).getLeft();     //获取点击控件与父控件左侧的距离
                int width=listmenus.get(i).getMeasuredWidth();   //获得控件本身宽度
                int toX=left+width/2-screenWitdth/2;
                //使条目移动到居中显示
                hs_menus.smoothScrollTo(toX, 0);

            }
            else{
                listmenus.get(i).setTextColor(0xFF929292);

            }

        }
    }

    private void initMenus() {
        listmenus = new ArrayList<TextView>();
        Menus.add("全部");
        Menus.add("娱乐");
        Menus.add("生活");
        Menus.add("运动");
        Menus.add("穿搭");
        Menus.add("美食");
        Menus.add("动漫");
        Menus.add("学习");
        Menus.add("搞笑");
        Menus.add("其他");

    }

    @Override
    public void initData() {                    //加载网络请求的数据，若无数据要求可不用重写
        super.initData();
//        fragmentList = new ArrayList<>();
//        fragmentList.add(new TabFragment(notes));
//        typeAdapter = new TypeAdapter(getActivity().getSupportFragmentManager(),fragmentList,Menus);
//        vp_type.setAdapter(typeAdapter);

        fragmentList = new ArrayList<>();
      //  for(int i= 0;i<Menus.size();i++)
       // {
        fragmentList.add(new TabFragment(notes,20));fragmentList.add(new TabFragment(notes2,21));
        fragmentList.add(new TabFragment(notes3,22));fragmentList.add(new TabFragment(notes4,23));
        fragmentList.add(new TabFragment(notes5,24));fragmentList.add(new TabFragment(notes6,25));
        fragmentList.add(new TabFragment(notes7,26));fragmentList.add(new TabFragment(notes8,27));
        fragmentList.add(new TabFragment(notes9,28));fragmentList.add(new TabFragment(notes10,29));
       // }
        typeAdapter = new TypeAdapter(getActivity().getSupportFragmentManager(),fragmentList,Menus);
        vp_type.setAdapter(typeAdapter);
        vp_type.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelectMenu(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void init_note() {              //获取笔记信息
        new Thread( ){            //创建一个子线程
            public void run() {
                Log.i("测试结果", "测试2");
                Message msg = new Message();
                msg.obj = "子线程111";
                notelist = new ArrayList<>().toArray(new List[0]);
                NoteDao noteDao = new NoteDao();                 //创建一个noteDao
                if(search_note==null||search_note=="")
                notes = noteDao.find_all_note();           //获取所有笔记
                else{
                    notes = noteDao.search_note_by_text(search_note);
                    search_note ="";
                }
                notes2 = noteDao.find_note_by_type(Menus.get(1));
                notes3 = noteDao.find_note_by_type(Menus.get(2));
                notes4 = noteDao.find_note_by_type(Menus.get(3));
                notes5 = noteDao.find_note_by_type(Menus.get(4));
                notes6 = noteDao.find_note_by_type(Menus.get(5));
                notes7 = noteDao.find_note_by_type(Menus.get(6));
                notes8= noteDao.find_note_by_type(Menus.get(7));
                notes9 = noteDao.find_note_by_type(Menus.get(8));
                notes10 = noteDao.find_note_by_type(Menus.get(9));


//                for(int i =1;i<Menus.size();i++)
//                {
//                    Log.i("获取分类成功","分类查询");
//                    notelist[i] = noteDao.find_note_by_type(Menus.get(i));
//                }

                Log.i("测试结果", "home笔记数量" + notes.size());

                mHander.sendMessage(msg);
            };
        }.start();


    }

    Handler mHander = new Handler(){             //创建一个主线程监听，当子线程init_note（）执行完毕后，执行下面initData（）方法；
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    Log.i("获取子线程","获取子线程"+msg.obj);
                    initData();
                    break;
                default:break;
            }

        }
    };


}
