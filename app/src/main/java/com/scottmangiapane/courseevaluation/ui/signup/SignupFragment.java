package com.scottmangiapane.courseevaluation.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scottmangiapane.courseevaluation.AsyncUtil;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.ui.MyDialog;
import com.scottmangiapane.courseevaluation.R;
import com.scottmangiapane.courseevaluation.ui.login.LoginFragment;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;

public class SignupFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        final TextView accountView=root.findViewById(R.id.accountView);
        final TextView nicknameVIew=root.findViewById(R.id.nicknameView);
        final Spinner schoolSpinner=root.findViewById(R.id.schoolspinner);
        final Spinner majorSpinner=root.findViewById(R.id.majorspinner);
        final TextView passwordView=root.findViewById(R.id.passwordView);
        Button signup = root.findViewById(R.id.signup);
        TextView gologin = root.findViewById(R.id.signed);


        //返回登录页面
        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.login_fragment,
                        new LoginFragment()).commit();
            }
        });


        //signup button event
        // if sign up success,then login and start MainActivity
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=accountView.getText().toString();
                String password=passwordView.getText().toString();
                String nickname=nicknameVIew.getText().toString();
                int schoolID=schoolSpinner.getSelectedItemPosition();
                int majorID=majorSpinner.getSelectedItemPosition();
                RequestParams rp=new RequestParams();
                rp.put("UserID",userID);
                rp.put("password",password);
                rp.put("nickname",nickname);
                rp.put("schoolID",schoolID);
                rp.put("majorID",majorID);

                AsyncUtil.post("/signup", rp, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String jsonString=new String(responseBody, StandardCharsets.UTF_8);
                        System.out.println(jsonString);
                        Intent intent=new Intent(getContext(), MainActivity.class);
                        intent.putExtra("userJson",jsonString);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        MyDialog mydialog=new MyDialog(getContext(),R.style.AccountDialog);
                        mydialog.setTitle("Sign up fail");
                        mydialog.setContent("Check your web connect");
                    }
                });
            }
        });
        return root;
    }
}