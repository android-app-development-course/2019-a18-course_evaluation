package com.scottmangiapane.courseevaluation.ui.footprint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.R;

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
     * 组件集合，对应course_list.xml中的控件
     */
    public final class courseItem {
        //public ImageView image;
        public TextView title;
        public Button detail_btn;
        public TextView desciption;
        public TextView teacher;
        public TextView period;
        public CardView card;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        courseItem courseItem=null;
        if(convertView==null){
            courseItem=new courseItem();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.course_list2, null);
            //courseItem.image=(ImageView)convertView.findViewById(R.id.image);
            courseItem.card=(CardView)convertView.findViewById(R.id.progressCard);
            courseItem.title=(TextView)convertView.findViewById(R.id.courseTitle);
            courseItem.detail_btn=(Button)convertView.findViewById(R.id.courseBtn1);
            courseItem.desciption=(TextView)convertView.findViewById(R.id.courseDescription);
            courseItem.period=(TextView)convertView.findViewById(R.id.courseRunDescription);
            courseItem.teacher=(TextView)convertView.findViewById(R.id.courseInstructors);

            convertView.setTag(courseItem);
        }else{
            courseItem=(courseItem)convertView.getTag();
        }
        //绑定数据
       // courseItem.image.setBackgroundResource((Integer)data.get(position).get("image"));
        courseItem.title.setText((String)data.get(position).get("title"));
        courseItem.teacher.setText((String)data.get(position).get("teacher"));
        courseItem.desciption.setText((String)data.get(position).get("desciption"));
        courseItem.period.setText((String)data.get(position).get("period"));
       courseItem.detail_btn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               //Toast.makeText(context,"click once" ,Toast.LENGTH_LONG).show();
               //传值跳转
               Intent intent=new Intent(context, CourseDetailActivity.class);
               //Bundle bundle=new Bundle();
               //bundle.putSerializable("key",mList.get(position));
               //intent.putExtras(bundle);
               //利用上下文开启跳转
               context.startActivity(intent);
           }
       });

        return convertView;
    }

}
