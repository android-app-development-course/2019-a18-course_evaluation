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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.scottmangiapane.courseevaluation.ClassData.UserModel;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.R;

import java.util.ArrayList;
import java.util.List;
import com.scottmangiapane.courseevaluation.MainActivity;

public class FootprintFragment extends Fragment {


    //各类初始的view
    private View viewContent;
    private TabLayout my_tablayout;
    private ViewPager my_viewpager;
    public static FootprintFragment myTabFragment;


    MainActivity mainActivity;

    private int mode = TabLayout.MODE_FIXED;

    private String userid;

    public FootprintFragment() {
        // Required empty public constructor
    }
    //对应动态加载的viewmodel
    //private FootPrintViewModel footprintViewModel;

    //对应的两个tab
    private LinearLayout mTabstar;
    private LinearLayout mTabparticipate;

    //对应的fragment
    private Fragment mFragstar;
    private  Fragment mFragparticipate;

    //对应的按钮
    private Button btnstar;
    private Button btnparticipate;

    //获取floating bar from activity

    private  FloatingActionButton fab;

    /**********************footprint *************************************************/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_footprint,container,false);
        initConentView(viewContent);

        mainActivity = (MainActivity) getActivity();




        initData();
        return viewContent;
    }

    public void initConentView(View viewContent) {
        this.my_tablayout = (TabLayout) viewContent.findViewById(R.id.my_tablayout);
        this.my_viewpager = (ViewPager) viewContent.findViewById(R.id.my_viewpager);
    }

    public void initData() {
        //创建一个viewpager的adapter


        //if (((MainActivity) mainActivity).getUserid()==null){}
        userid=mainActivity.getUserid();

        System.out.println("在footprint获取的userid:"+userid);



        TabFragmentAdapter adapter = new TabFragmentAdapter(getFragmentManager());
        List<Fragment> fragments = new ArrayList<Fragment>();

        if(userid!=null) {
            fragments.add(new ParticipateFragment(userid));//传入userid
            fragments.add(new StarFragment(userid));
        }
        else{
            fragments.add(new ParticipateFragment());//传入userid
            fragments.add(new StarFragment());
        }

        //隐藏floating bar


        String[] titlesArr = {"我的参与", "我的收藏"};
        adapter.setTitlesArr(titlesArr);
        adapter.setFragments(fragments);
        this.my_viewpager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        this.my_tablayout.setupWithViewPager(this.my_viewpager);
        my_tablayout.setTabMode(mode);
    }




}







