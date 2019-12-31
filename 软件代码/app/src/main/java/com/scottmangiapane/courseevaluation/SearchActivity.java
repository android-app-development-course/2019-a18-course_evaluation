package com.scottmangiapane.courseevaluation;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.scottmangiapane.courseevaluation.ClassData.CourseModel;
import com.scottmangiapane.courseevaluation.adapter.CourseListAdapter;

import com.scottmangiapane.courseevaluation.util.CheckUtils;
import com.scottmangiapane.courseevaluation.util.net.CommonHttpUtil;
import com.scottmangiapane.courseevaluation.util.net.URLProtocol;

import java.util.ArrayList;
import java.util.List;

import com.scottmangiapane.courseevaluation.util.AsyncUtil;
import com.scottmangiapane.courseevaluation.util.ToastUtil;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SearchActivity extends AppCompatActivity {

//    private Spinner spinner;
    private SearchView searchView;
    private String key;
    private String type;
    private Button zykBtn;
    private Button ggkBtn;
    private Button gxkBtn;
    private Button fzskBtn;

    private ListView list_view;
    private List<CourseModel> courseList = new ArrayList<CourseModel>();
    private CourseListAdapter adapter;
//    private AppCompatButton courseDetailBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        list_view=findViewById(R.id.list_view);

        searchView=findViewById(R.id.searchView);
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                //获取指定条件课程
//                if(TextUtils.isEmpty(key)){
//                    CommonHttpUtil.requestNet(handler, URLProtocol.searchcourse,"type="+type);
//                } else {
//                    CommonHttpUtil.requestNet(handler, URLProtocol.searchcourse,"type="+type,"keyword="+key);
//                }
                return true;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                int color0 = ((ColorDrawable)zykBtn.getBackground()).getColor();
                int color1 = ((ColorDrawable)ggkBtn.getBackground()).getColor();
                int color2 = ((ColorDrawable)gxkBtn.getBackground()).getColor();
                int color3 = ((ColorDrawable)fzskBtn.getBackground()).getColor();

                //找出绿色按钮
                if(color0==color1){
                    if(color2==color0){
                        type="3";
                    } else {
                        type="2";
                    }
                } else {
                    if(color0==color2){
                        type="1";
                    } else {
                        type="0";
                    }
                }

                //获取指定条件课程
                key=searchView.getQuery().toString();
                if(TextUtils.isEmpty(key)){
                    CommonHttpUtil.requestNet(handler, URLProtocol.searchcourse,"type="+type);
                } else {
                    CommonHttpUtil.requestNet(handler, URLProtocol.searchcourse,"type="+type,"keyword="+key);
                }
                return true;
            }
        });

        //专业课-按钮
        zykBtn=findViewById(R.id.zykBtn);zykBtn.setTag("0");
        zykBtn.setOnClickListener(selectBtnListener);
        zykBtn.setBackgroundColor(getResources().getColor(R.color.green));
        //公共课-按钮
        ggkBtn=findViewById(R.id.ggkBtn);ggkBtn.setTag("1");
        ggkBtn.setOnClickListener(selectBtnListener);
        //公选课-按钮
        gxkBtn=findViewById(R.id.gxkBtn);gxkBtn.setTag("2");
        gxkBtn.setOnClickListener(selectBtnListener);
        //非正式课-按钮
        fzskBtn=findViewById(R.id.fzskBtn);fzskBtn.setTag("3");
        fzskBtn.setOnClickListener(selectBtnListener);

        zykBtn.setBackgroundColor(getResources().getColor(R.color.grey_transparent));
        ggkBtn.setBackgroundColor(getResources().getColor(R.color.white));
        gxkBtn.setBackgroundColor(getResources().getColor(R.color.white));
        fzskBtn.setBackgroundColor(getResources().getColor(R.color.white));

        //获取全部课程
        CommonHttpUtil.requestNet(handler, URLProtocol.allcourses);

//        courseDetailBtn=findViewById(R.id.courseBtn1);
//        courseDetailBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(SearchActivity.this, CourseDetailActivity.class);
//                SearchActivity.this.startActivity(intent);
//            }
//        });


    }

    View.OnClickListener selectBtnListener =new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            zykBtn.setBackgroundColor(getResources().getColor(R.color.white));
            ggkBtn.setBackgroundColor(getResources().getColor(R.color.white));
            gxkBtn.setBackgroundColor(getResources().getColor(R.color.white));
            fzskBtn.setBackgroundColor(getResources().getColor(R.color.white));
            v.setBackgroundColor(getResources().getColor(R.color.grey_transparent));
            String tag=v.getTag().toString();
            type=tag;
            key=searchView.getQuery().toString();
            //获取指定条件课程
            if(TextUtils.isEmpty(key)){
                CommonHttpUtil.requestNet(handler, URLProtocol.searchcourse,"type="+tag);
            } else {
                CommonHttpUtil.requestNet(handler, URLProtocol.searchcourse,"type="+tag,"keyword="+key);
            }
        }
    };

    //创建一个handler
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!CheckUtils.isEmpty(msg.obj)) {
                JSONArray jsonArray =  JSON.parseArray(msg.obj.toString());
                courseList = com.alibaba.fastjson.JSONObject.parseArray(jsonArray.toJSONString(), CourseModel.class);
                if(!CheckUtils.isEmpty(courseList) && courseList.size()>0){
                    for (CourseModel um:courseList){
                        //Toast.makeText(getActivity(), um.getName(), Toast.LENGTH_LONG).show();
                        adapter = new CourseListAdapter(SearchActivity.this, R.layout.course_list_view_item, courseList);
                        adapter.notifyDataSetChanged();
                        list_view.setAdapter(adapter);
                    }
                } else {
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                    ToastUtil.showToast(SearchActivity.this,"没找到符合条件的数据!",false);
                    //Toast.makeText(SearchActivity.this, "没找到符合条件的数据!", Toast.LENGTH_SHORT).show();
                }

            } else {
                adapter.clear();
                adapter.notifyDataSetChanged();
                ToastUtil.showToast(SearchActivity.this,"没找到符合条件的数据!",false);
                //Toast.makeText(SearchActivity.this, "没找到符合条件的数据!", Toast.LENGTH_SHORT).show();
            }

        }
    };

}
