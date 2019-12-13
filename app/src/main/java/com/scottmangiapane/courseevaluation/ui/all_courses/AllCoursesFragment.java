package com.scottmangiapane.courseevaluation.ui.all_courses;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.scottmangiapane.courseevaluation.ClassData.CourseInfo;
import com.scottmangiapane.courseevaluation.ClassData.CourseModel;
import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.R;
import com.scottmangiapane.courseevaluation.adapter.CourseListAdapter;
import com.scottmangiapane.courseevaluation.util.CheckUtils;
import com.scottmangiapane.courseevaluation.util.net.CommonHttpUtil;
import com.scottmangiapane.courseevaluation.util.net.URLProtocol;

import java.util.ArrayList;
import java.util.List;

public class AllCoursesFragment extends Fragment {

    private AllCoursesViewModel allCoursesViewModel;
    private ListView list_view;
    private List<CourseModel> courseList = new ArrayList<CourseModel>();

    //    private AppCompatButton courseDetailBtn;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_allcourses2, container, false);

        list_view=root.findViewById(R.id.list_view);


//        courseDetailBtn=root.findViewById(R.id.courseBtn1);
//        courseDetailBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), CourseDetailActivity.class);
//                getActivity().startActivity(intent);
//            }
//        });

        //获取全部课程
        CommonHttpUtil.requestNet(handler, URLProtocol.allcourses);

        return root;
    }

    //创建一个handler
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!CheckUtils.isEmpty(msg.obj)) {
                JSONArray jsonArray =  JSON.parseArray(msg.obj.toString());
                courseList = com.alibaba.fastjson.JSONObject.parseArray(jsonArray.toJSONString(), CourseModel.class);
                for (CourseModel um:courseList){
                    //Toast.makeText(getActivity(), um.getName(), Toast.LENGTH_LONG).show();
                    CourseListAdapter adapter = new CourseListAdapter(getActivity(), R.layout.course_list_view_item, courseList);
                    list_view.setAdapter(adapter);
                }
            } else {
                Toast.makeText(getActivity(), "未获得数据，获取失败!", Toast.LENGTH_LONG).show();
            }

        }
    };

}