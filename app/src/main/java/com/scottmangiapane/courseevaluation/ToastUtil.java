package com.scottmangiapane.courseevaluation;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil {
    public static void showToast(Context context,String content, boolean isPositive){
        Toast toast = Toast.makeText(context, null, Toast.LENGTH_LONG);
        toast.setText(content);
        toast.setGravity(Gravity.CENTER, 0, 0);
        ImageView imageView = new ImageView(context);
        if(isPositive)
            imageView.setImageResource(R.drawable.check_circle_fill);
        else
            imageView.setImageResource(R.drawable.ic_close_fill);
        LinearLayout toastView = (LinearLayout) toast.getView();//获得toast的布局
        TextView messageTextView = (TextView) toastView.getChildAt(0);
        messageTextView.setTextSize(25);
        messageTextView.setGravity(Gravity.CENTER);
        toastView.setOrientation(LinearLayout.VERTICAL);//横向布局
        toastView.addView(imageView, 0);//将ImageView在加入到此布局中的第一个位置
        toast.show();
    }
}
