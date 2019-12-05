package com.scottmangiapane.courseevaluation.ui.footprint;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.R;

import java.util.ArrayList;
import java.util.List;

public class FootprintFragment extends Fragment {


    //各类初始的view
    private View viewContent;
    private TabLayout my_tablayout;
    private ViewPager my_viewpager;
    public static FootprintFragment myTabFragment;

    private int mode = TabLayout.MODE_FIXED;

    public FootprintFragment() {
        // Required empty public constructor
    }


    //对应动态加载的viewmodel
    private FootPrintViewModel footprintViewModel;

    //对应的两个tab
    private LinearLayout mTabstar;
    private LinearLayout mTabparticipate;

    //对应的fragment
    private Fragment mFragstar;
    private  Fragment mFragparticipate;

    //对应的按钮
    private Button btnstar;
    private Button btnparticipate;


    /**********************footprint *************************************************/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_footprint,container,false);
        initConentView(viewContent);
        initData();
        return viewContent;
    }

    public void initConentView(View viewContent) {
        this.my_tablayout = (TabLayout) viewContent.findViewById(R.id.my_tablayout);
        this.my_viewpager = (ViewPager) viewContent.findViewById(R.id.my_viewpager);
    }

    public void initData() {
        //创建一个viewpager的adapter
        TabFragmentAdapter adapter = new TabFragmentAdapter(getFragmentManager());
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new ParticipateFragment());
        fragments.add(new StarFragment());
        String[] titlesArr = {"我的参与", "我的收藏"};
        adapter.setTitlesArr(titlesArr);
        adapter.setFragments(fragments);
        this.my_viewpager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        this.my_tablayout.setupWithViewPager(this.my_viewpager);
        my_tablayout.setTabMode(mode);
    }

}



/*
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        footprintViewModel =
                ViewModelProviders.of(this).get(FootPrintViewModel.class);

       View root = inflater.inflate(R.layout.fragment_footprint, container, false);

       //初始化两个tab布局
        mTabparticipate = (LinearLayout)root.findViewById(R.id.footprint_tab_participate);
        mTabstar = (LinearLayout) root.findViewById(R.id.footprint_tab_star);

        //初始化四个Tab的点击事件

//       mTabparticipate.setOnClickListener(this);
  //      mTabstar.setOnClickListener(this);
        //initViews();//初始化控件
        //initEvents();//初始化事件
        selectTab(0);//默认选中第一个Tab

        return root;
    }


   /* private void initEvents() {
        //初始化四个Tab的点击事件
        mTabparticipate.setOnClickListener(this);
        mTabstar.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        //先将四个ImageButton置为灰色
        //resetImgs();
        switch (v.getId()) {
            case R.id.id_tab_participate:
                selectTab(0);//当点击的是微信的Tab就选中微信的Tab
                break;
            case R.id.id_tab_star:
                selectTab(1);
                break;

        }

    }



    //将四个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (mFragparticipate != null) {
            transaction.hide(mFragparticipate);
        }
        if (mFragstar != null) {
            transaction.hide(mFragstar);
        }

    }

    //进行选中Tab的处理
    private void selectTab(int i) {
        //获取FragmentManager对象
        FragmentManager manager = getChildFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        switch (i) {
            //当选中点击的是微信的Tab时
            case 0:
                //设置微信的ImageButton为绿色
                //mWeixinImg.setImageResource(R.mipmap.tab_weixin_pressed);
                //如果微信对应的Fragment没有实例化，则进行实例化，并显示出来
                if (mFragparticipate == null) {
                    mFragparticipate = new ParticipateFragment();
                    transaction.add(R.id.footprint_content, mFragparticipate);
                } else {
                    //如果微信对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mFragparticipate);
                }
                break;
            case 1:
                //mFrdImg.setImageResource(R.mipmap.tab_find_frd_pressed);
                if (mFragstar == null) {
                    mFragstar= new StarFragment();
                    transaction.add(R.id.footprint_content, mFragstar);
                } else {
                    transaction.show(mFragstar);
                }
                break;

        }
        //不要忘记提交事务
        transaction.commit();
    }
    */




