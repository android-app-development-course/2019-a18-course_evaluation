package com.scottmangiapane.courseevaluation.ui.login;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.*;
import com.scottmangiapane.courseevaluation.AsyncUtil;
import com.scottmangiapane.courseevaluation.ClassData.UserModel;
import com.scottmangiapane.courseevaluation.LoginActivity;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.MyDialog;
import com.scottmangiapane.courseevaluation.R;
import com.scottmangiapane.courseevaluation.ui.signup.SignupFragment;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;


public class LoginFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        //立即注册按钮
        TextView toSignup = root.findViewById(R.id.signup_now);
        //登录按钮
        Button login_now = root.findViewById(R.id.login_now);
        //直接进入按钮
        Button toHome = root.findViewById(R.id.skip);
        //账号编辑
        final EditText account=root.findViewById(R.id.accountView);
        //password editText
        final EditText password=root.findViewById(R.id.passwordView);
        //替换为注册fragment
        toSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right,R.animator.slide_out_left,R.animator.slide_in_right,R.animator.slide_out_left)
                        .addToBackStack("2").
                        replace(R.id.login_fragment,new SignupFragment()).commit();
            }
        });
        //打开MainActivity
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        //登录事件
        login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MyDialog myDialog=new MyDialog(getContext(),R.style.AccountDialog);
                String accountString=account.getText().toString();
                String passwordString=password.getText().toString();
                RequestParams requestParams=new RequestParams();
                requestParams.add("UserID",accountString);
                requestParams.add("password",passwordString);


                AsyncUtil.post("/login",requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String jsonString=new String(responseBody, StandardCharsets.UTF_8);
                        System.out.println(jsonString);
                        Intent intent=new Intent(getContext(),MainActivity.class);
                        intent.putExtra("userJson",jsonString);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        myDialog.setTitle("Login Fail");
                        myDialog.setTitle("Check your input and try again");
                        myDialog.show();
                    }
                });
            }
        });

//        loginViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                toSignup.setText(s);
//            }
//        });


        account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){//用户点击了账号输入框，在输入框下方弹出账号记录框。
                    Dialog accountDialog;
                    accountDialog=new AlertDialog.Builder(getContext()).setView(R.layout.account_record).create();
                    accountDialog.show();
                    Window window=accountDialog.getWindow();
                    window.setBackgroundDrawableResource(android.R.color.white);
                    WindowManager.LayoutParams lp=window.getAttributes();
                    lp.gravity=Gravity.START|Gravity.TOP;
                    int[]loc=new int[2];
                    account.getLocationInWindow(loc);
                    lp.width=account.getWidth();
                    lp.height=account.getHeight()*3;
                    lp.x=loc[0]+5;
                    lp.y=loc[1]+15;
                    lp.dimAmount=0f;

                    window.setAttributes(lp);

                }else{

                }
            }
        });
        return root;
    }
}