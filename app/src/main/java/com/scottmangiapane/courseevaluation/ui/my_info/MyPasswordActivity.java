package com.scottmangiapane.courseevaluation.ui.my_info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scottmangiapane.courseevaluation.AsyncUtil;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.R;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cz.msebera.android.httpclient.Header;

public class MyPasswordActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button btn_sure,btn_forget;
    private EditText et_oldpassword,et_newpassword,et_surepassword;
    private String oldpassword,newpassword,surepassword;
    private String majorID,nickname,password,schoolID,userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mypassword);

        String userJson=null;
        Intent intent=getIntent();
        userJson=intent.getStringExtra("userJson");
        if(userJson!=null) {
            parseJSONWithJSONObject(userJson);
        }
        else{
            System.out.println("error");
        }

        et_oldpassword=(EditText)findViewById(R.id.et_oldpassword);
        et_newpassword=(EditText)findViewById(R.id.et_newpassword);
        et_surepassword=(EditText)findViewById(R.id.et_surepassword);
        btn_sure=(Button)findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(MyPasswordActivity.this);
        btn_forget=(Button)findViewById(R.id.btn_sure);
        btn_forget.setOnClickListener(MyPasswordActivity.this);

    }

    @Override
    public void onClick(View view) {
        String str="";
        ImageView imageView = new ImageView(getApplicationContext());
        if(view.getId()==R.id.btn_sure){
            oldpassword=et_oldpassword.getText().toString();
            newpassword=et_newpassword.getText().toString();
            surepassword=et_surepassword.getText().toString();
            if(!newpassword.equals(surepassword)) {
                str="两次密码输入不一致";
                imageView.setImageResource(R.drawable.ic_close_fill);
            }
            if(!oldpassword.equals(password)) {
                str="原密码输入错误";
                imageView.setImageResource(R.drawable.ic_close_fill);
            }
            if(newpassword.equals(surepassword)&&oldpassword.equals(password)) {
                str = "修改成功";
                imageView.setImageResource(R.drawable.check_circle_fill);
                password=newpassword;
                MainActivity.password=password;
                RequestParams rp=new RequestParams();
                rp.put("userID",userID);
                rp.put("password",password);

                AsyncUtil.get("/set/password", rp, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String jsonString=new String(responseBody, StandardCharsets.UTF_8);
                        //Intent intent=new Intent(MyNameActivity.this, MyInfoFragment.getContext());
                        //intent.putExtra("userJson",jsonString);
                        //startActivity(intent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("error",error.toString());
                    }
                });
            }

            Toast toast = Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_LONG);
            toast.setText(str);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout toastView = (LinearLayout) toast.getView();//获得toast的布局
            TextView messageTextView = (TextView) toastView.getChildAt(0);
            messageTextView.setTextSize(25);
            messageTextView.setGravity(Gravity.CENTER);
            toastView.setOrientation(LinearLayout.VERTICAL);//横向布局
            toastView.addView(imageView, 0);//将ImageView在加入到此布局中的第一个位置
            toast.show();
        }
        if(view.getId()==R.id.btn_forget){
            Toast toast = Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_LONG);
            toast.setText("目前还没有相关设置哦~");
            toast.show();
        }
    }

    private void parseJSONWithJSONObject(String JsonData) {
        try {
            JSONObject jsonObject = new JSONObject(JsonData);
            this.majorID = jsonObject.getString("majorID");
            this.nickname = jsonObject.getString("nickname");
            this.password = jsonObject.getString("password");
            this.schoolID  = jsonObject.getString("schoolID");
            this.userID  = jsonObject.getString("userID");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
