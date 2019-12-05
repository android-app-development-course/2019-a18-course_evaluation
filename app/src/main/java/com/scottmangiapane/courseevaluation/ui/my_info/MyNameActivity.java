package com.scottmangiapane.courseevaluation.ui.my_info;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scottmangiapane.courseevaluation.R;
import androidx.appcompat.app.AppCompatActivity;

public class MyNameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sure;
    private EditText et_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_myname);
        Intent intent=getIntent();
        //String queryResultStr=intent.getStringExtra("nickname");
        String []arr=new String[2];
        arr=intent.getStringArrayExtra("nickname");
        et_username=(EditText)findViewById(R.id.et_userName);
        et_username.setText(arr[1]);
        btn_sure=(Button)findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(MyNameActivity.this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_sure){
            et_username.getText().toString();
            Toast toast = Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG);
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
        }

    }
}
