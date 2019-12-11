package com.scottmangiapane.courseevaluation.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.scottmangiapane.courseevaluation.R;

public class MyDialog extends Dialog {
    private TextView Title;
    private Button Close;
    private TextView Content;

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.alert_dialog);
        Title=findViewById(R.id.alertTitle);
        Close=findViewById(R.id.alertCancle);
        Content=findViewById(R.id.alertContent);
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.gravity= Gravity.CENTER;
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    public void setTitle(String title) {
        Title.setText(title);
    }

    public void setContent(String content) {
        Content.setText(content);
    }
}
