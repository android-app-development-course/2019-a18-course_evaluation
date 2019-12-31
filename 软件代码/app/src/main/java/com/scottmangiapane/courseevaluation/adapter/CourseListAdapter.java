package com.scottmangiapane.courseevaluation.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.alibaba.fastjson.JSON;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.BitmapCallback;
import com.scottmangiapane.courseevaluation.ClassData.CourseModel;
import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.R;

import java.util.List;

import okhttp3.Response;


public class CourseListAdapter extends ArrayAdapter<CourseModel> {
    private int recourceId;
    private Context ct;
    /*
    ImageListAdapter( Context context,  int resource,  List<ImageListArray> objects)解析
    Context context ：当前类或者当前类的Context上下文
    int resource  ：ListView的一行布局，它将会导入到适配器中与数据自动适配
    List<ImageListArray> objects ：数据的List集合
     */
    public CourseListAdapter(Context context, int resource, List<CourseModel> objects) {
        super(context, resource, objects);
        ct=context;
        recourceId = resource;
    }

    @NonNull
    @Override
    /*
    为什么要重写getView？因为适配器中其实自带一个返回布局的方法，
    这个方法可以是自定义适配一行的布局显示，因为我们需要更复杂的布局内容，
    所以我们直接重写它，，不需要在导入一个简单的TextView或者ImageView布局让适配器在写入布局数据。
    所以在recourceId自定义布局id直接导入到getView里面，getView方法不在convertView中获取布局了。
    最后只要返回一个view布局就可以。
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CourseModel course = getItem(position); //得到集合中指定位置的一组数据，并且实例化
        View view = LayoutInflater.from(getContext()).inflate(R.layout.course_list_view_item,parent,false); //用布局裁剪器(又叫布局膨胀器)，将导入的布局裁剪并且放入到当前布局中

        TextView courseName = (TextView)view.findViewById(R.id.courseRunDescription); //从裁剪好的布局里获取TextView布局Id
        courseName.setText(course.getAcademy());

        //TextView course_id = (TextView)view.findViewById(R.id.course_id); //从裁剪好的布局里获取TextView布局Id
        //course_id.setText(course.getCourseID().toString());

        TextView teacherview = (TextView)view.findViewById(R.id.courseInstructors); //从裁剪好的布局里获取TextView布局Id
        teacherview.setText(course.getTeacher());

        RatingBar courseRatingBar = (RatingBar)view.findViewById(R.id.courseRatingBar); //从裁剪好的布局里获取TextView布局Id
        courseRatingBar.setRating(course.getScore());

        TextView courseRatingTv = (TextView)view.findViewById(R.id.courseRatingTv); //从裁剪好的布局里获取TextView布局Id
        courseRatingTv.setText(course.getScore().toString());

        TextView courseTitle = (TextView)view.findViewById(R.id.courseTitle); //从裁剪好的布局里获取TextView布局Id
        courseTitle.setText(course.getName());

        TextView courseDescription = (TextView)view.findViewById(R.id.courseDescription); //从裁剪好的布局里获取TextView布局Id
        courseDescription.setText(course.getDetail());

        AppCompatButton button =(AppCompatButton)view.findViewById(R.id.courseBtn1);
        button.setTag(course.getCourseID().toString());

        //添加进度条设置
        ProgressBar progressbar=(ProgressBar)view.findViewById(R.id.courseProgress);
        progressbar.setProgress(6*Integer.valueOf(course.getCom_num()));

        progressbar.setMax(100);//设置进度条的最大值


        final String courseJson = JSON.toJSONString(course);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ct, CourseDetailActivity.class);
                System.out.println("获取到的coursejson为"+courseJson);
                intent.putExtra("coursejson",courseJson);
                ct.startActivity(intent);
            }
        });

        return view;
    }

}