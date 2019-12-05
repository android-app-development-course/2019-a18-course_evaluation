package com.scottmangiapane.courseevaluation.ui.my_info;

import android.os.Bundle;
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

import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.R;

import androidx.appcompat.app.AppCompatActivity;

public class MyMajorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sure;
    private Spinner sp_myschool, sp_mymajor;
    private int myschoolpos = 0, mymajorpos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mymajor);
        sp_myschool = (Spinner) findViewById(R.id.sp_myschool);
        sp_mymajor = (Spinner) findViewById(R.id.sp_mymajor);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(MyMajorActivity.this);

        sp_myschool.setSelection(myschoolpos, true);
        sp_mymajor.setSelection(mymajorpos, true);
        sp_myschool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //score = getResources().getStringArray(R.array.score)[pos].toString();
                myschoolpos = pos;
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
                mymajorpos = pos;
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
            sp_myschool.setSelection(myschoolpos, true);
            sp_mymajor.setSelection(mymajorpos, true);
            //Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_LONG).show();

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
