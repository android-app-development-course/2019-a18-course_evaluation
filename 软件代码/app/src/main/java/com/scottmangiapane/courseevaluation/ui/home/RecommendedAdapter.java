package com.scottmangiapane.courseevaluation.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.scottmangiapane.courseevaluation.ClassData.CourseModel;
import com.scottmangiapane.courseevaluation.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendedAdapter extends BaseAdapter {
    private Map<Integer,String>map;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<CourseModel> data;
    private int layoutID;

    //增加完整的Json数据String
    private JSONObject jsondata;

    public void setData(List<CourseModel> data) {
        this.data = data;
    }



    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getCourseID();
    }

    public RecommendedAdapter( Context context, List<CourseModel> data, int layoutID) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        this.layoutID = layoutID;
        map=new HashMap<>();
        map.put(0,"专业课");
        map.put(1,"公共课");
        map.put(2,"公选课");
        map.put(3,"非正式课");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.recommended_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.setNameView((TextView) convertView.findViewById(R.id.course_name));
            viewHolder.setTeacherView((TextView)convertView.findViewById(R.id.course_teacher));
            viewHolder.setTypeView((TextView)convertView.findViewById(R.id.course_type));
            viewHolder.setScoreView((RatingBar)convertView.findViewById(R.id.courseRatingBar));

            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.getNameView().setText(data.get(position).getName());
        viewHolder.getTeacherView().setText(data.get(position).getTeacher());
        viewHolder.getTypeView().setText(map.get(data.get(position).getType()));
        viewHolder.getScoreView().setRating(data.get(position).getScore());
        return convertView;
    }

    private final class ViewHolder{
        TextView nameView;
        TextView typeView;
        TextView teacherView;
        RatingBar scoreView;

        public void setNameView(TextView nameView) {
            this.nameView = nameView;
        }

        public void setTypeView(TextView typeView) {
            this.typeView = typeView;
        }

        public void setTeacherView(TextView teacherView) {
            this.teacherView = teacherView;
        }

        public void setScoreView(RatingBar scoreView) {
            this.scoreView = scoreView;
        }

        public TextView getNameView() {
            return nameView;
        }

        public TextView getTypeView() {
            return typeView;
        }

        public TextView getTeacherView() {
            return teacherView;
        }

        public RatingBar getScoreView() {
            return scoreView;
        }
    }
}
