package com.scottmangiapane.courseevaluation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private LinearLayout ll_courseCommentList;
    private CourseCommentList courseCommentList;
    private TextView tv_courseName, tv_courseType, tv_teachername, tv_coursescore, tv_summarize;
    private Button btn_opentest, btn_opentest1, btn_closetest, btn_closetest1, btn_smalltest, btn_smalltest1;
    private Button btn_questionpoints, btn_questionpoints1, btn_presentation, btn_presentation1, btn_paper, btn_paper1;
    private Button btn_signin, btn_signin1, btn_others, btn_others1;
    private EditText et_mycomment;
    private Spinner sp_myscore;
    private Button btn_comment, btn_collect;
    private RatingBar rb_coursescore;
    private int num;
    private boolean flag = false;
    private String score, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_coursedetail);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        //基本信息
        tv_courseName = (TextView) findViewById(R.id.tv_coursename);
        tv_courseType = (TextView) findViewById(R.id.tv_courseType);
        tv_teachername = (TextView) findViewById(R.id.tv_teachername);
        tv_coursescore = (TextView) findViewById(R.id.tv_coursescore);
        rb_coursescore = (RatingBar) findViewById(R.id.rb_coursescore);
        btn_collect=(Button)findViewById(R.id.btn_collect);

        //概述
        tv_summarize = (TextView) findViewById(R.id.tv_summarize);

        //评论功能
        sp_myscore = (Spinner) findViewById(R.id.sp_myscore);
        et_mycomment = (EditText) findViewById(R.id.et_mycomment);
        btn_comment = (Button) findViewById(R.id.btn_comment);

        //考核方式按钮点击事件
        //开卷考试
        btn_opentest = (Button) findViewById(R.id.btn_opentest);
        btn_opentest1 = (Button) findViewById(R.id.btn_opentest1);
        //闭卷考试
        btn_closetest = (Button) findViewById(R.id.btn_closetest);
        btn_closetest1 = (Button) findViewById(R.id.btn_closetest1);
        //课堂小测
        btn_smalltest = (Button) findViewById(R.id.btn_smalltest);
        btn_smalltest1 = (Button) findViewById(R.id.btn_smalltest1);
        //课堂提问
        btn_questionpoints = (Button) findViewById(R.id.btn_questionpoints);
        btn_questionpoints1 = (Button) findViewById(R.id.btn_questionpoints1);
        //ppt展示
        btn_presentation = (Button) findViewById(R.id.btn_presentation);
        btn_presentation1 = (Button) findViewById(R.id.btn_presentation1);
        //论文
        btn_paper = (Button) findViewById(R.id.btn_paper);
        btn_paper1 = (Button) findViewById(R.id.btn_paper1);
        //点名签到
        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signin1 = (Button) findViewById(R.id.btn_signin1);
        //其他
        btn_others = (Button) findViewById(R.id.btn_others);
        btn_others1 = (Button) findViewById(R.id.btn_others1);

        //评论
        ll_courseCommentList=(LinearLayout)findViewById(R.id.ll_courseCommentList);
        courseCommentList=new CourseCommentList(this);//这里传this
        courseCommentList.setData(1,"4分","老师人很好","2019-11-11","听见下雨的声音");
    }

    private void initEvent() {
        tv_courseName.setText("编译原理");
        tv_courseType.setText("理学");
        tv_teachername.setText("王欣明");
        tv_coursescore.setText("3.5分");
        rb_coursescore.setRating((float) 3.5);
        tv_summarize.setText("讲授操作系统基本原理与拓展，让同学深入了解操作系统的工作过程");
        btn_collect.setOnClickListener(CourseDetailActivity.this);
        btn_closetest.setOnClickListener(CourseDetailActivity.this);
        btn_closetest1.setOnClickListener(CourseDetailActivity.this);
        btn_smalltest.setOnClickListener(CourseDetailActivity.this);
        btn_smalltest1.setOnClickListener(CourseDetailActivity.this);
        btn_questionpoints.setOnClickListener(CourseDetailActivity.this);
        btn_questionpoints1.setOnClickListener(CourseDetailActivity.this);
        btn_presentation.setOnClickListener(CourseDetailActivity.this);
        btn_presentation1.setOnClickListener(CourseDetailActivity.this);
        btn_paper.setOnClickListener(CourseDetailActivity.this);
        btn_paper1.setOnClickListener(CourseDetailActivity.this);
        btn_signin.setOnClickListener(CourseDetailActivity.this);
        btn_signin1.setOnClickListener(CourseDetailActivity.this);
        btn_opentest.setOnClickListener(CourseDetailActivity.this);
        btn_opentest1.setOnClickListener(CourseDetailActivity.this);
        btn_others.setOnClickListener(CourseDetailActivity.this);
        btn_others1.setOnClickListener(CourseDetailActivity.this);

        sp_myscore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                score = getResources().getStringArray(R.array.score)[pos].toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    //加载评论
    private void initData() {
        ll_courseCommentList.addView(courseCommentList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_collect:
                Toast toast = Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_LONG);
                toast.show();
                break;
            case R.id.btn_opentest:
            case R.id.btn_opentest1:
                btn_opentest.setText("100");
                btn_opentest.setEnabled(false);
                btn_opentest1.setEnabled(false);
                break;
            case R.id.btn_closetest:
            case R.id.btn_closetest1:
                btn_closetest.setText("100");
                btn_closetest.setEnabled(false);
                btn_closetest1.setEnabled(false);
                break;
            case R.id.btn_smalltest:
            case R.id.btn_smalltest1:
                btn_smalltest.setText("100");
                btn_smalltest.setEnabled(false);
                btn_smalltest1.setEnabled(false);
                break;
            case R.id.btn_questionpoints:
            case R.id.btn_questionpoints1:
                btn_questionpoints.setText("100");
                btn_questionpoints.setEnabled(false);
                btn_questionpoints1.setEnabled(false);
                break;
            case R.id.btn_presentation:
            case R.id.btn_presentation1:
                btn_presentation.setText("100");
                btn_presentation.setEnabled(false);
                btn_presentation1.setEnabled(false);
                break;
            case R.id.btn_paper:
            case R.id.btn_paper1:
                btn_paper.setText("100");
                btn_paper.setEnabled(false);
                btn_paper1.setEnabled(false);
                break;
            case R.id.btn_signin:
            case R.id.btn_signin1:
                btn_signin.setText("100");
                btn_signin.setEnabled(false);
                btn_signin1.setEnabled(false);
                break;
            case R.id.btn_others:
            case R.id.btn_others1:
                btn_others.setText("100");
                btn_others.setEnabled(false);
                btn_others1.setEnabled(false);
                break;
            case R.id.btn_comment:
                comment = et_mycomment.getText().toString();
                Date date = new Date();
                date.getTime();
                String dateStr = sdf.format(date);
                //tv_commentdate.setText(dateStr);
                break;
        }
    }
}