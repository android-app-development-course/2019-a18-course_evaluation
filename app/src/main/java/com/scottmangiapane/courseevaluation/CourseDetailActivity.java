package com.scottmangiapane.courseevaluation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scottmangiapane.courseevaluation.ui.my_info.MyInfoFragment;
import com.scottmangiapane.courseevaluation.ui.my_info.MyNameActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

public class CourseDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
    private LinearLayout ll_courseCommentList;
    private CourseCommentList []courseCommentList;
    private TextView tv_courseName, tv_courseType, tv_teachername, tv_coursescore, tv_summarize,tv_courseAcademy;
    private Button btn_opentest, btn_opentest1, btn_closetest, btn_closetest1, btn_smalltest, btn_smalltest1,btn_signin, btn_signin1;
    private Button btn_questionpoints, btn_questionpoints1, btn_presentation, btn_presentation1, btn_paper, btn_paper1,btn_others, btn_others1;
    private Button btn_comment, btn_collect;
    private EditText et_mycomment;
    private Spinner sp_myscore;
    private RatingBar rb_coursescore;
    private int score,com_num,type,courseID,imageID;
    private String comment,dateStr,userID;//我的评论
    private String [][]commentarr;
    private String courseJson;
    private String detail,name,teacher,academy,typestr;
    private int nullflag = 0;//用于判断是否有登录，没有登录为0
    private int commentflag=0;//用于判断是否有评论，没有评论为0

    //考核方式数量
    private int closet=0,opent=0,other=0,paper=0,ppt=0,ques=0,sign=0,smallt=0;//总的
    private int mycloset=0,myopent=0,myother=0,mypaper=0,myppt=0,myques=0,mysign=0,mysmallt=0;//我的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_coursedetail);
        userID=MainActivity.userID;
        if(userID!=null){
            nullflag=1;
            imageID=MainActivity.imageID;
        }
        //获取课程信息json
        Intent intent=getIntent();
        courseJson=intent.getStringExtra("coursejson");
        if(courseJson!=null) {
            //parseJSONWithJSONObject(courseJson);
            coursedetailJSONObject(courseJson);
        }
        else{
            System.out.println("error");
        }
        new TimeThread().start(); //启动新的线程
        System.out.println("com_num长度："+com_num);
        courseCommentList=new CourseCommentList[com_num];
        commentarr = new String [ com_num ][ ];
        for(int i=0;i<com_num;i++){
            commentarr[i]=new String [4];
        }

        initView();
        initEvent();
//        initData();
    }

    private void initView() {
        //基本信息
        tv_courseName = (TextView) findViewById(R.id.tv_coursename);
        tv_courseType = (TextView) findViewById(R.id.tv_courseType);
        tv_teachername = (TextView) findViewById(R.id.tv_teachername);
        tv_coursescore = (TextView) findViewById(R.id.tv_coursescore);
        tv_courseAcademy=(TextView) findViewById(R.id.tv_courseAcademy);
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

        //获取评论列表
        RequestParams requestParams=new RequestParams();
        requestParams.add("courseID",courseID+"");
        AsyncUtil.post("/coursecomment", requestParams ,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String jsonString=new String(responseBody, StandardCharsets.UTF_8);
                //解析并加载进列表中
                com_num=parseJSONWithJSONObject(jsonString);
                initData();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("error");
                Log.e("e",error.toString());
            }
        });

        for(int i=0;i<com_num;i++) {
            courseCommentList[i] = new CourseCommentList(this);//这里传this
        }
    }

    private void initEvent() {
        tv_courseName.setText(name);
        tv_courseType.setText(typestr);
        tv_courseAcademy.setText(academy);
        tv_teachername.setText(teacher);
        tv_coursescore.setText(score+"分");
        rb_coursescore.setRating((float) score);
        tv_summarize.setText(detail);
        btn_collect.setOnClickListener(CourseDetailActivity.this);

        RequestParams requestParams=new RequestParams();
        requestParams.add("courseID",courseID+"");

        AsyncUtil.post("/courseexam", requestParams ,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String jsonString=new String(responseBody, StandardCharsets.UTF_8);
                courseexamJSONObject(jsonString);
                System.out.println(jsonString);
                btn_closetest.setText(closet+"");
                btn_smalltest.setText(smallt+"");
                btn_questionpoints.setText(ques+"");
                btn_presentation.setText(ppt+"");
                btn_paper.setText(paper+"");
                btn_signin.setText(sign+"");
                btn_opentest.setText(opent+"");
                btn_others.setText(other+"");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println(error.toString());
            }
        });

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
                score = 5-pos;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    //加载评论
    private void initData() {
        for(int i=0;i<com_num;i++) {
                ll_courseCommentList.addView(courseCommentList[i]);
        }
    }

    @Override
    public void onClick(View view) {
        RequestParams rp=new RequestParams();
        switch (view.getId()) {
            case R.id.btn_collect:
                if(nullflag==0){

                    System.out.println("没有登录！！！");

                    Toast toast = Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_LONG);
                    toast.setText("请先登录~");
                    toast.show();
                }else {

                    System.out.println("已经登录！！！用户id"+userID);

                    Toast toast = Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_LONG);
                    toast.setText("收藏成功~");
                    toast.show();
                    rp.put("userID", userID);
                    rp.put("courseID", courseID);
                    AsyncUtil.get("/collectcourse", rp, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String jsonString = new String(responseBody, StandardCharsets.UTF_8);
                            System.out.println("scuess");
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("error", error.toString());
                        }
                    });
                }
                break;
            case R.id.btn_opentest:
            case R.id.btn_opentest1:
                opent+=1;
                myopent=1;
                btn_opentest.setText(opent+"");
                btn_opentest.setEnabled(false);
                btn_opentest1.setEnabled(false);
                break;
            case R.id.btn_closetest:
            case R.id.btn_closetest1:
                closet+=1;
                mycloset=1;
                btn_closetest.setText(closet+"");
                btn_closetest.setEnabled(false);
                btn_closetest1.setEnabled(false);
                break;
            case R.id.btn_smalltest:
            case R.id.btn_smalltest1:
                smallt+=1;
                mysmallt=1;
                btn_smalltest.setText(smallt+"");
                btn_smalltest.setEnabled(false);
                btn_smalltest1.setEnabled(false);
                break;
            case R.id.btn_questionpoints:
            case R.id.btn_questionpoints1:
                ques+=1;
                myques=1;
                btn_questionpoints.setText(ques+"");
                btn_questionpoints.setEnabled(false);
                btn_questionpoints1.setEnabled(false);
                break;
            case R.id.btn_presentation:
            case R.id.btn_presentation1:
                ppt+=1;
                myppt=1;
                btn_presentation.setText(ppt+"");
                btn_presentation.setEnabled(false);
                btn_presentation1.setEnabled(false);
                break;
            case R.id.btn_paper:
            case R.id.btn_paper1:
                paper+=1;
                mypaper=1;
                btn_paper.setText(paper+"");
                btn_paper.setEnabled(false);
                btn_paper1.setEnabled(false);
                break;
            case R.id.btn_signin:
            case R.id.btn_signin1:
                sign+=1;
                mysign=1;
                btn_signin.setText(sign+"");
                btn_signin.setEnabled(false);
                btn_signin1.setEnabled(false);
                break;
            case R.id.btn_others:
            case R.id.btn_others1:
                other+=1;
                myother=1;
                btn_others.setText(other+"");
                btn_others.setEnabled(false);
                btn_others1.setEnabled(false);
                break;
            case R.id.btn_comment:
                if(nullflag==0){
                    Toast toast = Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_LONG);
                    toast.setText("请先登录~");
                    toast.show();
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_LONG);
                    toast.setText("评论成功~");
                    toast.show();
                    comment = et_mycomment.getText().toString();
                    Date date = new Date();
                    date.getTime();
                    dateStr = sdf.format(date);
                    System.out.println("Score:" + score + "+Comment:" + comment + "+Date:" + dateStr);
                    rp = new RequestParams();
                    rp.put("userID", userID);
                    rp.put("courseID", courseID);
                    rp.put("score", score);
                    rp.put("date", dateStr);
                    rp.put("comment", comment);
                    rp.put("opent", myopent);
                    rp.put("closet", mycloset);
                    rp.put("smallt", mysmallt);
                    rp.put("ques", myques);
                    rp.put("ppt", myppt);
                    rp.put("paper", mypaper);
                    rp.put("sign", mysign);
                    rp.put("other", myother);
                    AsyncUtil.get("/addcomment", rp, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String jsonString = new String(responseBody, StandardCharsets.UTF_8);
                            System.out.println("success");
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("error", error.toString());
                        }
                    });
                    commentflag=1;
                }
                break;
        }
    }

    private void courseexamJSONObject(String JsonData) {
        try {
            JSONObject jsonObject = new JSONObject(JsonData);
            this.courseID = Integer.parseInt(jsonObject.getString("courseID"));
            this.closet = Integer.parseInt(jsonObject.getString("closet"));
            this.opent = Integer.parseInt(jsonObject.getString("opent"));
            this.other = Integer.parseInt(jsonObject.getString("other"));
            this.paper  = Integer.parseInt(jsonObject.getString("paper"));
            this.ppt  = Integer.parseInt(jsonObject.getString("ppt"));
            this.ques  = Integer.parseInt(jsonObject.getString("ques"));
            this.sign  = Integer.parseInt(jsonObject.getString("sign"));
            this.smallt  = Integer.parseInt(jsonObject.getString("smallt"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void coursedetailJSONObject(String JsonData) {
        try {
            JSONObject jsonObject = new JSONObject(JsonData);
            this.name = jsonObject.getString("name");
            this.detail = jsonObject.getString("detail");
            this.teacher = jsonObject.getString("teacher");
            this.academy=jsonObject.getString("academy");
            this.courseID=Integer.parseInt(jsonObject.getString("courseID"));
            this.com_num = Integer.parseInt(jsonObject.getString("com_num"));
            this.score  = Integer.parseInt(jsonObject.getString("score"));
            this.type  = Integer.parseInt(jsonObject.getString("type"));
            typestr =this.getResources().getStringArray(R.array.major)[type];
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private int parseJSONWithJSONObject(String JsonData) {
        try {
            JSONArray jsonArray=new JSONArray(JsonData);
            System.out.println("jsonArray长度："+jsonArray.length());
            for (int i=0; i < jsonArray.length(); i++)    {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println(jsonObject.toString());
                commentarr[i][0]= jsonObject.getString("comment");
                commentarr[i][1]= jsonObject.getString("date");
                commentarr[i][2]= jsonObject.getString("nickname");
                commentarr[i][3]= jsonObject.getString("score");
                int ran = jsonObject.getInt("imageID");
                courseCommentList[i].setData(ran, commentarr[i][3]+"分", commentarr[i][0], commentarr[i][1], commentarr[i][2]);
            }
            return jsonArray.length();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public void setNewCommentList(){
        //加载新的评论
        int ran = imageID%7+1;
        CourseCommentList courseCommentList=new CourseCommentList(this);
        courseCommentList.setData(ran, score+"分", comment, dateStr, MainActivity.nickname);
        ll_courseCommentList.addView(courseCommentList);
        System.out.println("加载新的评论");
    }

    public void setNewScore(){
        RequestParams rp=new RequestParams();
        rp.put("type",type);
        rp.put("keyword",name);
        AsyncUtil.get("/searchcourse", rp, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String jsonString=new String(responseBody, StandardCharsets.UTF_8);
                System.out.println("===========");
                System.out.println("newSocreString:"+jsonString);
                ScoreJSONObject(jsonString);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("error",error.toString());
            }
        });
    }

    private void ScoreJSONObject(String JsonData) {
        try {
            JSONArray jsonArray=new JSONArray(JsonData);
            System.out.println("jsonArray长度："+jsonArray.length());
            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(teacher.equals(jsonObject.getString("teacher"))) {
                    this.score = Integer.parseInt(jsonObject.getString("score"));
                    tv_coursescore.setText(score + "分");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                System.out.println("handler准备创建新的Commentlist");
                setNewCommentList();
                setNewScore();
            }
        }
    };

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    if (commentflag==1) {
                        System.out.println("线程准备传送数据");
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                        commentflag=0;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

}