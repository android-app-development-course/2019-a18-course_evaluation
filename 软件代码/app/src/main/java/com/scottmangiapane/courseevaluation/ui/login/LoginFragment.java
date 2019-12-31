package com.scottmangiapane.courseevaluation.ui.login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.loopj.android.http.*;
import com.scottmangiapane.courseevaluation.util.AsyncUtil;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.R;
import com.scottmangiapane.courseevaluation.util.ToastUtil;
import com.scottmangiapane.courseevaluation.ui.MyDialog;
import com.scottmangiapane.courseevaluation.ui.signup.SignupFragment;

import java.nio.charset.StandardCharsets;


import cz.msebera.android.httpclient.Header;


public class LoginFragment extends Fragment {
    private View root ;
    //立即注册按钮
    private TextView toSignup;
    //登录按钮
    private Button login_now;
    //直接进入按钮
    private Button toHome;
    //账号编辑
    private EditText account;
    //password editText
    private EditText password;
    //remember password checkbox
    private CheckBox checkBox;
    //account dialog
    private MyDialog accountDialog;
    //remembered accounts sp
    private SharedPreferences indexsp;
    //account index
    private SharedPreferences.Editor indexeditor;
    private SharedPreferences accountsp;
    private SharedPreferences.Editor accounteditor;

    private SharedPreferences passwordsp;
    private SharedPreferences.Editor passwordeditor;

    private void initView(){
        //立即注册按钮
        toSignup = root.findViewById(R.id.signup_now);
        //登录按钮
        login_now = root.findViewById(R.id.login_now);
        //直接进入按钮
        toHome = root.findViewById(R.id.skip);
        //账号编辑
        account=root.findViewById(R.id.accountView);
        //password editText
        password=root.findViewById(R.id.passwordView);
        //remember password checkbox
        checkBox=root.findViewById(R.id.checkbox);



        //remembered accounts sp
        indexsp=getContext().getSharedPreferences("index",Context.MODE_PRIVATE);
        //account index
        indexeditor=indexsp.edit();
        if(indexsp.getAll().size()==0)
            indexeditor.putInt("index",-1).commit();


        accountsp=getContext().getSharedPreferences("account",Context.MODE_PRIVATE);
        accounteditor=accountsp.edit();


        passwordsp=getContext().getSharedPreferences("password",Context.MODE_PRIVATE);
        passwordeditor=passwordsp.edit();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        root=inflater.inflate(R.layout.fragment_login,container,false);

        //initialize the vars
        initView();
        //when account view clicked, show remembered accounts
        account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){//用户点击了账号输入框，在输入框下方弹出账号记录框。
                    showAccountRecord();
                }else{
                    accountDialog.dismiss();
                }
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accountDialog.isShowing())accountDialog.dismiss();
                else accountDialog.show();
            }
        });

        login_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountString=account.getText().toString();
                String passwordString=password.getText().toString();
                if(accountString==null||accountString.equals("")){
                    ToastUtil.showToast(getContext(),"请输入账号",false);
                    return ;
                }
                if(passwordString==null||passwordString.equals("")){
                    ToastUtil.showToast(getContext(),"请输入密码",false);
                    return ;
                }
                if(checkBox.isChecked())
                    rememberPassword(accountString,passwordString);


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
                        ToastUtil.showToast(getContext(),"登录成功",true);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        if(statusCode==500){
                            ToastUtil.showToast(getContext(),"用户名或密码错误",false);
                        }
                    }
                });
            }
        });

        toSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_right,R.animator.slide_out_left,R.animator.slide_in_right,R.animator.slide_out_left)
                        .addToBackStack("2").
                        replace(R.id.login_fragment,new SignupFragment()).commit();
            }
        });

        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), MainActivity.class);
                intent.putExtra("userJson","");
                startActivity(intent);
            }
        });
        return root;
    }



    public void rememberPassword(String accountString,String passwordString){
        int index=indexsp.getInt("index",-1);
        if(index<4)
            index++;
        else
            index=0;
        String record_account="account"+index;

        if(passwordsp.getString(accountString,null)!=null){
            passwordeditor.putString(accountString,passwordString).commit();
        }

        else {
            accounteditor.putString(record_account, accountString).commit();
            passwordeditor.putString(accountString, passwordString).commit();
            indexeditor.putInt("index", index).commit();
        }

        System.out.println("index"+index);
    }

    public void showAccountRecord(){
        if(accountDialog!=null)
            if(accountDialog.isShowing())accountDialog.dismiss();

        accountDialog=new MyDialog(getContext(),R.style.AccountDialog);
        accountDialog.setAccount(account);
        accountDialog.setPassword(password);
        accountDialog.show();
        Window window=accountDialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.white);
        WindowManager.LayoutParams lp=window.getAttributes();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        lp.gravity=Gravity.START|Gravity.TOP;
        int[]loc=new int[2];
        account.getLocationInWindow(loc);
        lp.width=account.getWidth();
        lp.height=account.getHeight();
        lp.x=loc[0]+5;
        lp.y=loc[1]+15;
        lp.dimAmount=0f;

        window.setAttributes(lp);
    }

}