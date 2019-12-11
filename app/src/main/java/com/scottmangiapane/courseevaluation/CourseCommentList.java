package com.scottmangiapane.courseevaluation;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CourseCommentList extends LinearLayout implements View.OnClickListener {

    private ImageView iv_head;
    private TextView tv_username,tv_commentdate,tv_myscore,tv_mycomment;
    private Button btn_course_good,btn_course_bad;
    private Context context;

    public CourseCommentList(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.course_comment_list,this);//第二个参数必须传this
        initView();
        initEvent();
        this.context=context;
    }

    private void initEvent() {
        btn_course_good.setOnClickListener(this);
        btn_course_bad.setOnClickListener(this);
    }

    private void initView() {
        iv_head=(ImageView)findViewById(R.id.iv_head);
        tv_username=(TextView)findViewById(R.id.tv_username);
        tv_commentdate=(TextView)findViewById(R.id.tv_commentdate);
        tv_myscore=(TextView)findViewById(R.id.tv_myscore);
        tv_mycomment=(TextView)findViewById(R.id.tv_mycomment);
        btn_course_good=(Button)findViewById(R.id.btn_course_good);
        btn_course_bad=(Button)findViewById(R.id.btn_course_bad);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_course_good:
                btn_course_good.setEnabled(false);
                break;
            case R.id.btn_course_bad:
                btn_course_bad.setEnabled(false);
                break;
        }
    }

    public void setData(int imgID,String myscore,String mycomment,String commentdate,String username){
        switch (imgID) {
            case 1:
                iv_head.setImageResource(R.drawable.img_myhead1);
            case 2:
                iv_head.setImageResource(R.drawable.img_myhead2);
            case 3:
                iv_head.setImageResource(R.drawable.img_myhead3);
            case 4:
                iv_head.setImageResource(R.drawable.img_myhead4);
            case 5:
                iv_head.setImageResource(R.drawable.img_myhead5);
            case 6:
                iv_head.setImageResource(R.drawable.img_myhead6);
            case 7:
                iv_head.setImageResource(R.drawable.img_myhead7);
        }
        tv_myscore.setText(myscore);
        tv_mycomment.setText(mycomment);
        tv_commentdate.setText(commentdate);
        tv_username.setText(username);
    }

}