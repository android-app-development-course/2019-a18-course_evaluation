package com.scottmangiapane.courseevaluation.ui;

import android.app.Dialog;
import android.content.Context;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import com.scottmangiapane.courseevaluation.R;

import java.util.Collection;
import java.util.Map;

public class MyDialog extends Dialog {
    private ListView listView;
    private EditText account;
    private EditText password;

    public void setAccount(EditText account) {
        this.account = account;
    }

    public void setPassword(EditText password) {
        this.password = password;
    }

    public MyDialog(@NonNull final Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.account_record);
        listView=findViewById(R.id.accounts);
        final SharedPreferences sharedPreferences=context.getSharedPreferences("account",Context.MODE_PRIVATE);
        final SharedPreferences passwordsp=context.getSharedPreferences("password",Context.MODE_PRIVATE);
        Map<String, ?> map=sharedPreferences.getAll();
        final int size=map.size();
        System.out.println("mapsize:"+size);

        if (size!=0){
            String []array=new String[size];
            map.values().toArray(array);
            System.out.println(map.values());

            ArrayAdapter<String>adapter;
            adapter=new ArrayAdapter<String>(context,R.layout.account_listitem,array);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("position+"+position);
                    String accountString=sharedPreferences.getString(("account"+(size-position-1)),null);
                    account.setText(accountString);
                    String passwordString=passwordsp.getString(accountString,null);
                    password.setText(passwordString);
                    dismiss();
                }
            });


        }
    }




}