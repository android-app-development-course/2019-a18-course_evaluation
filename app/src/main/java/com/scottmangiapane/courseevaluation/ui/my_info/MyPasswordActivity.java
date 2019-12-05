package com.scottmangiapane.courseevaluation.ui.my_info;

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

public class MyPasswordActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button btn_sure;
    private EditText et_oldpassword,et_newpassword,et_password;
    private String oldpassword,newpassword,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mypassword);
        et_oldpassword=(EditText)findViewById(R.id.et_oldpassword);
        et_newpassword=(EditText)findViewById(R.id.et_newpassword);
        et_password=(EditText)findViewById(R.id.et_password);
        btn_sure=(Button)findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(MyPasswordActivity.this);
    }

    @Override
    public void onClick(View view) {
        String str="";
        ImageView imageView = new ImageView(getApplicationContext());
        if(view.getId()==R.id.btn_sure){
            oldpassword=et_oldpassword.getText().toString();
            newpassword=et_newpassword.getText().toString();
            password=et_password.getText().toString();
            if(newpassword==password) {
                str = "修改成功";
                imageView.setImageResource(R.drawable.check_circle_fill);
            }
            if(newpassword!=password) {
                str="两次密码输入不一致";
                imageView.setImageResource(R.drawable.ic_close_fill);
            }
            Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
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
