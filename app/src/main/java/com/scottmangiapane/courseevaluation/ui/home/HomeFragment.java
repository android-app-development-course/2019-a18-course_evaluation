package com.scottmangiapane.courseevaluation.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.alibaba.fastjson.JSONArray;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.scottmangiapane.courseevaluation.AsyncUtil;
import com.scottmangiapane.courseevaluation.ClassData.CourseModel;
import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.R;
import com.scottmangiapane.courseevaluation.SearchActivity;
import com.scottmangiapane.courseevaluation.ToastUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {

    //    private HomeViewModel homeViewModel;
    private ListView course_listView;
    private List<List<CourseModel>>course_lists;
    private final String URL="/recommended?type=";
    private View root;
    private RecommendedAdapter recommendedAdapter;
    private RadioGroup radioGroup;

    //Fll--搜索按钮
    private Button toSearchBtn ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        course_listView=root.findViewById(R.id.recommended_list);
        radioGroup=root.findViewById(R.id.home_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                List<CourseModel>temp;
                switch(checkedId){
                    case R.id.newest_button:
                        temp=course_lists.get(0);
                        if (temp!=null)
                            recommendedAdapter.setData(temp);
                        else
                            ToastUtil.showToast(getContext(),"数据加载失败",false);
                        break;
                    case R.id.highest_button:
                        temp=course_lists.get(1);
                        if (temp!=null)
                            recommendedAdapter.setData(temp);
                        else
                            ToastUtil.showToast(getContext(),"数据加载失败",false);
                        break;
                    case R.id.most_button:
                        temp=course_lists.get(2);
                        if (temp!=null)
                            recommendedAdapter.setData(temp);
                        else
                            ToastUtil.showToast(getContext(),"数据加载失败",false);
                        break;
                }
                course_listView.setAdapter(recommendedAdapter);
            }
        });
        initLists();


        /***************Fll 搜索界面跳转******************************/
        toSearchBtn = root.findViewById(R.id.toSearchBtn);
        toSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return root;
    }

    public void initLists(){
        course_lists=new Vector<>(3);
        recommendedAdapter=new RecommendedAdapter(getContext(),null,R.layout.recommended_item);
        for(int i=0;i<3;i++){
            String url=URL+i;
            AsyncUtil.get(url , null, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String jsonString=new String(responseBody,StandardCharsets.UTF_8);
                    List<CourseModel>temp= (JSONArray.parseArray(jsonString,CourseModel.class));
                    course_lists.add(temp);
                    if(course_lists.size()==1){
                        recommendedAdapter.setData(temp);
                        course_listView.setAdapter(recommendedAdapter);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    ToastUtil.showToast(getContext(),"网络连接失败",false);
                }
            });
        }

        /*********修改查看课程详情的问题****************************/
        course_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String coursejson=JSONArray.toJSONString(course_listView.getAdapter().getItem(position));
                    Intent intent=new Intent(getContext(), CourseDetailActivity.class);
                    intent.putExtra("coursejson",coursejson);
                    startActivity(intent);
            }
        });
    }

}