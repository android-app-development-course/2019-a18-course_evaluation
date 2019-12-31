package com.scottmangiapane.courseevaluation.ui.contact_us;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.scottmangiapane.courseevaluation.CourseDetailActivity;
import com.scottmangiapane.courseevaluation.MainActivity;
import com.scottmangiapane.courseevaluation.R;
import com.scottmangiapane.courseevaluation.util.ToastUtil;

import org.json.JSONObject;

public class ContactFragment extends Fragment {

    private Button submitbtn;
    private EditText title;
    private EditText description;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        submitbtn=(Button) root.findViewById(R.id.okBtn);


        //String editTitle=title.getText().toString();
        //String editDescp=description.getText().toString();

        submitbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ToastUtil.showToast(getContext(),"提交成功！",true);
                //回到首页
                Intent intent = new Intent(getContext(), MainActivity.class);

                startActivity(intent);
            }
        });

        //传输bug 数据  title + description
        return root;
    }



}