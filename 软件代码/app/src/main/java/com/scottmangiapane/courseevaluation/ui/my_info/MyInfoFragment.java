package com.scottmangiapane.courseevaluation.ui.my_info;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scottmangiapane.courseevaluation.util.AsyncUtil;
import com.scottmangiapane.courseevaluation.ClassData.UserModel;
import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.R;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cz.msebera.android.httpclient.Header;

public class MyInfoFragment extends Fragment {
    private View viewContent;
    private Button btn_mymajor, btn_myname, btn_mypassword, btn_setting, btn_about;
    private TextView tv_username;//用户昵称
    private ImageView iv_head;//用户头像
    private String nickname, password, userID;
    private int nullflag = 0, imageID;//用于判断是否有登录，没有登录为0
    MainActivity mainActivity;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_myinfo, container, false);
        mainActivity = (MainActivity) getActivity();
        userID = mainActivity.getUserid();
        if (userID != null) {
            password = mainActivity.getPassword();
            nickname = mainActivity.getNickname();
            imageID = mainActivity.getImageID();
            nullflag = 1;
        } else {
            nickname = "登录后设置昵称~";
        }

        new TimeThread().start(); //启动新的线程
        initView(viewContent);
        initEvent(viewContent);
        return viewContent;
    }


    public void initView(View viewContent) {
        this.tv_username = (TextView) viewContent.findViewById(R.id.tv_username);
        this.iv_head = (ImageView) viewContent.findViewById(R.id.iv_head);
        this.btn_mymajor = (Button) viewContent.findViewById(R.id.btn_mymajor);
        this.btn_myname = (Button) viewContent.findViewById(R.id.bt_myname);
        this.btn_mypassword = (Button) viewContent.findViewById(R.id.bt_mypassword);
        this.btn_setting = (Button) viewContent.findViewById(R.id.bt_setting);
        this.btn_about = (Button) viewContent.findViewById(R.id.bt_about);
    }

    public void initEvent(View viewContent) {
        tv_username.setText(nickname);
        chooseHead(viewContent,imageID);
        btn_mymajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nullflag == 0) {
                    Toast.makeText(getActivity(), "请先登录~", Toast.LENGTH_SHORT).show();
                } else {
                    RequestParams requestParams = new RequestParams();
                    requestParams.add("UserID", userID);
                    requestParams.add("password", password);
                    AsyncUtil.post("/login", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String jsonString = new String(responseBody, StandardCharsets.UTF_8);
                            Intent intent = new Intent(getContext(), MyMajorActivity.class);
                            intent.putExtra("userJson", jsonString);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("e", error.toString());
                        }
                    });
                }
            }
        });
        btn_myname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nullflag == 0) {
                    Toast.makeText(getActivity(), "请先登录~", Toast.LENGTH_SHORT).show();
                } else {
                    RequestParams requestParams = new RequestParams();
                    requestParams.add("UserID", userID);
                    requestParams.add("password", password);
                    AsyncUtil.post("/login", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String jsonString = new String(responseBody, StandardCharsets.UTF_8);
                            Intent intent = new Intent(getContext(), MyNameActivity.class);
                            intent.putExtra("userJson", jsonString);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("e", error.toString());
                        }
                    });
                }
            }
        });
        btn_mypassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nullflag == 0) {
                    Toast.makeText(getActivity(), "请先登录~", Toast.LENGTH_SHORT).show();
                } else {
                    RequestParams requestParams = new RequestParams();
                    requestParams.add("UserID", userID);
                    requestParams.add("password", password);
                    AsyncUtil.post("/login", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String jsonString = new String(responseBody, StandardCharsets.UTF_8);
                            Intent intent = new Intent(getContext(), MyPasswordActivity.class);
                            intent.putExtra("userJson", jsonString);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("e", error.toString());
                        }
                    });
                }
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "目前还不能设置哦~", Toast.LENGTH_SHORT).show();
            }
        });
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "目前还没有相关信息哦~", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chooseHead(View viewContent,int imageID) {
        int ran = imageID%7+1;
        if (ran == 1) {
            iv_head.setImageResource(R.drawable.img_myhead1);
        }
        if (ran == 2) {
            iv_head.setImageResource(R.drawable.img_myhead2);
        }
        if (ran == 3) {
            iv_head.setImageResource(R.drawable.img_myhead3);
        }
        if (ran == 4) {
            iv_head.setImageResource(R.drawable.img_myhead4);
        }
        if (ran == 5) {
            iv_head.setImageResource(R.drawable.img_myhead5);
        }
        if (ran == 6) {
            iv_head.setImageResource(R.drawable.img_myhead6);
        }
        if (ran == 7) {
            iv_head.setImageResource(R.drawable.img_myhead7);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                tv_username.setText(nickname);
            }
        }
    };

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    if (MyNameActivity.nickname != nickname && MyNameActivity.nickname != null) {
                        nickname = MyNameActivity.nickname;
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                    if (MainActivity.password!=password){
                        password=MainActivity.password;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }
}
