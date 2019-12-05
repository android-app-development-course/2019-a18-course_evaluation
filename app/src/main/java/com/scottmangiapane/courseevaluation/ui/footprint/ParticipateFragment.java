package com.scottmangiapane.courseevaluation.ui.footprint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.R;
import com.scottmangiapane.courseevaluation.ui.all_courses.AllCoursesFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipateFragment extends Fragment {

    //private FragmentManager fmanager;
    //private FragmentTransaction ftransaction;
    private  Button btn1;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.footprint_tab_participate, container, false);


        listView = (ListView)root.findViewById(R.id.list_view);
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new FootprintAdapter(getActivity(), list));

        return root;
    }

    //列表展示

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
           // map.put("image", R.drawable.ic_announcement);
            map.put("title", "操作系统"+i);
            map.put("period", "开课时间2019,Jan" + i);
            map.put("teacher", "陈红英"+i);
            map.put("description", "操作系统原理"+i);
            map.put("detail_btn", R.id.courseBtn1);
            list.add(map);
        }
        return list;
    }



}