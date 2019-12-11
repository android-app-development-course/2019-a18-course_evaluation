package com.scottmangiapane.courseevaluation.ui.footprint;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.R;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FootprintAdapter extends BaseAdapter{

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public FootprintAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }

    /**
     * 组件集合，对应course_list.xml中的控件以及json data
     */
    public final class courseItem {
        //public ImageView image;
        public TextView title;      //课程名称
        public Button detail_btn;   //查看详情按钮
        public TextView desciption; //课程描述
        public TextView teacher;    //课程教师
        public TextView academy;     //开设学院
        public CardView card;
        public TextView ratingnumber;//评分数字



        public ProgressBar progressbar;//评论数量热度条

        public RatingBar ratingbar;//评论星星显示


    }
    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        courseItem courseItem=null;
        if(convertView==null){
            courseItem=new courseItem();

            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.course_list2, null);
            //courseItem.image=(ImageView)convertView.findViewById(R.id.image);
            courseItem.card=(CardView)convertView.findViewById(R.id.progressCard);
            courseItem.title=(TextView)convertView.findViewById(R.id.courseTitle);
            courseItem.detail_btn=(Button)convertView.findViewById(R.id.courseBtn1);
            courseItem.desciption=(TextView)convertView.findViewById(R.id.courseRunDescription);
            courseItem.academy=(TextView)convertView.findViewById(R.id.textView);
            courseItem.teacher=(TextView)convertView.findViewById(R.id.courseInstructors);
            courseItem.ratingnumber=(TextView)convertView.findViewById(R.id.courseRatingTv);

            //评分星星 与 进度条
            courseItem.ratingbar=(RatingBar) convertView.findViewById(R.id.courseRatingBar);
            courseItem.progressbar=(ProgressBar)convertView.findViewById(R.id.courseProgress);

            convertView.setTag(courseItem);
        }else{
            courseItem=(courseItem)convertView.getTag();
        }

        //获取数据
        String AverageRating = (String)data.get(position).get("ratingnumber");
        String CommentNum= (String)data.get(position).get("comment_num");

        //设置课程列表不同的背景的图片

       // courseItem.image.setBackgroundResource((Integer)data.get(position).get("image"));
        courseItem.title.setText((String)data.get(position).get("title"));
        courseItem.teacher.setText((String)data.get(position).get("teacher"));
        courseItem.desciption.setText((String)data.get(position).get("description"));
        courseItem.academy.setText((String)data.get(position).get("academy"));
        courseItem.ratingnumber.setText(AverageRating);


        //评分星星与进度条设置
        courseItem.ratingbar.setRating(Float.valueOf(AverageRating));
        courseItem.progressbar.setProgress(10*Integer.valueOf(CommentNum));

        courseItem.progressbar.setMax(100);//设置进度条的最大值

        //按钮部分--详情页面
        courseItem.detail_btn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               try {

                   //先获取对应的jsonobject
                   JSONObject coursejson = (JSONObject) data.get(position).get("jsondata");

                    //传给detail activity
                   Intent intent = new Intent(context, CourseDetailActivity.class);

                   intent.putExtra("coursejson", coursejson.toString());

                   context.startActivity(intent);
                   System.out.println("向detail activity传值完成");

               }
               catch (Exception e){
                   e.printStackTrace();
               }
           }
       });


        return convertView;
    }

}
