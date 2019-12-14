package com.scottmangiapane.courseevaluation.ui.my_info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.scottmangiapane.courseevaluation.AsyncUtil;
import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.R;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpResponseException;

public class MyMajorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sure;
    private Spinner sp_myschool, sp_mymajor;
    private int myschoolpos = 0, mymajorpos = 0;
    private String majorID,nickname,password,schoolID,userID;
    private int sID,mID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mymajor);

        String userJson=null;
        Intent intent=getIntent();
        userJson=intent.getStringExtra("userJson");
        System.out.println("userJson:"+userJson);
        if(userJson!=null) {
            parseJSONWithJSONObject(userJson);
        }
        else{
            System.out.println("error");
        }

        sp_myschool = (Spinner) findViewById(R.id.sp_myschool);
        sp_mymajor = (Spinner) findViewById(R.id.sp_mymajor);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(MyMajorActivity.this);

        sp_myschool.setSelection(sID, true);
        sp_mymajor.setSelection(mID, true);
        sp_myschool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //score = getResources().getStringArray(R.array.score)[pos].toString();
                sID = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        sp_mymajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //score = getResources().getStringArray(R.array.score)[pos].toString();
                mID=pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_sure) {
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

            RequestParams rp=new RequestParams();
            rp.put("majorID",mID);
            rp.put("schoolID",sID);
            rp.put("userID",userID);
            AsyncUtil.get("/set/schoolandmajor", rp, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String jsonString=new String(responseBody, StandardCharsets.UTF_8);
                    System.out.println("success");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.e("error",error.toString());
                }
            });
        }
        sp_myschool.setSelection(sID, true);
        sp_mymajor.setSelection(mID, true);
    }

    private void parseJSONWithJSONObject(String JsonData) {
        try {
            JSONObject jsonObject = new JSONObject(JsonData);
            this.majorID = jsonObject.getString("majorID");
            this.nickname = jsonObject.getString("nickname");
            this.password = jsonObject.getString("password");
            this.schoolID  = jsonObject.getString("schoolID");
            this.userID  = jsonObject.getString("userID");
            this.mID=Integer.parseInt(this.majorID);
            this.sID=Integer.parseInt(this.schoolID);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
