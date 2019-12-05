package com.scottmangiapane.courseevaluation.ui.my_info;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static java.lang.Thread.sleep;

public class MyInfoFragment extends Fragment {

    private ResultSet re;
    private Connection con;
    private Statement stmt;

    private View viewContent;
    private Button btn_mymajor, btn_myname, btn_mypassword, btn_setting, btn_about;
    private TextView tv_username;//用户昵称
    private ImageView iv_head;//用户头像

    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_myinfo, container, false);
        initView(viewContent);
        initEvent(viewContent);
        return viewContent;
    }

    public void initView(View viewContent) {
        this.tv_username=(TextView)viewContent.findViewById(R.id.tv_username);
        this.iv_head=(ImageView)viewContent.findViewById(R.id.iv_head);
        this.btn_mymajor = (Button)viewContent.findViewById(R.id.btn_mymajor);
        this.btn_myname = (Button)viewContent.findViewById(R.id.bt_myname);
        this.btn_mypassword = (Button)viewContent.findViewById(R.id.bt_mypassword);
        this.btn_setting = (Button)viewContent.findViewById(R.id.bt_setting);
        this.btn_about = (Button)viewContent.findViewById(R.id.bt_about);
    }

    public void initEvent(View viewContent){
        tv_username.setText("我的昵称");
        iv_head.setImageResource(R.drawable.img_myhead1);
        btn_mymajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyMajorActivity.class);
                startActivity(intent);
            }
        });
        btn_myname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyNameActivity.class);
                Bundle bundle = new Bundle();
                //String queryResultStr="听见声音";
                //bundle.putString("nickname",queryResultStr); //放入所需要传递的值
                String []arr=new String[2];
                arr[0]="userID";
                arr[1]="nickname";
                bundle.putStringArray("nickname",arr);
                intent.putExtras(bundle);
                getActivity().startActivity(intent); //这里一定要获取到所在Activity再startActivity()；
                // startActivity(intent);
            }
        });
        btn_mypassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyPasswordActivity.class);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"目前还不能设置哦~", Toast.LENGTH_SHORT).show();
            }
        });
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"目前还没有相关信息哦~", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
