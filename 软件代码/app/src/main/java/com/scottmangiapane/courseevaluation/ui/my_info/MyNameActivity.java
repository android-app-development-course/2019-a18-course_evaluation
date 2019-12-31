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
import com.scottmangiapane.courseevaluation.util.AsyncUtil;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.R;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cz.msebera.android.httpclient.Header;

public class MyNameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sure;
    private EditText et_username;
    private String majorID,password,schoolID,userID;
    public static String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_myname);
        String userJson=null;
        Intent intent=getIntent();
        userJson=intent.getStringExtra("userJson");
        if(userJson!=null) {
            parseJSONWithJSONObject(userJson);
        }
        else{
            System.out.println("error");
        }

        et_username=(EditText)findViewById(R.id.et_userName);
        et_username.setText(nickname);
        btn_sure=(Button)findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(MyNameActivity.this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_sure){
            nickname=et_username.getText().toString();
            Toast toast = Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_LONG);
            toast.setText("修改成功");
            toast.setGravity(Gravity.CENTER, 0, 0);
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(R.drawable.check_circle_fill);
            LinearLayout toastView = (LinearLayout) toast.getView();//获得toast的布局
            TextView messageTextView = (TextView) toastView.getChildAt(0);
            messageTextView.setTextSize(25);
            messageTextView.setGravity(Gravity.CENTER);
            toastView.setOrientation(LinearLayout.VERTICAL);//横向布局
            toastView.addView(imageView, 0);//将ImageView在加入到此布局中的第一个位置
            toast.show();
            MainActivity.nickname=nickname;
            RequestParams rp=new RequestParams();
            rp.put("userID",userID);
            rp.put("nickname",nickname);
            AsyncUtil.get("/set/nickname", rp, new AsyncHttpResponseHandler() {
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
