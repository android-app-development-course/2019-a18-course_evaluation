package com.scottmangiapane.courseevaluation;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.scottmangiapane.courseevaluation.ClassData.UserModel;
import com.scottmangiapane.courseevaluation.ui.my_info.MyInfoFragment;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private UserModel userModel;

    /**==============zpp============**/
    public static String userID;
    public static String nickname;
    public static String password;
    public static int imageID;

    private AppBarConfiguration mAppBarConfiguration;


    //http 网络请求
    String url="https://scnu-zjxw.cn";

    //发送一个简单的GET请求
    public void sendGet(View view) {
        //发送请求客户端
        AsyncHttpClient client = new AsyncHttpClient();
        //调用其get方法，参数1 URL
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //200 OK
                String info = new String(responseBody);
                System.out.println(info);
                Log.e("connect info:","connect successfully!");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Error:" + new String(responseBody));
            }
        });
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userJson=null;
        Intent intent=getIntent();
        userJson=intent.getStringExtra("userJson");
        if(userJson!=null){
            setUserModel(JSON.parseObject(userJson,UserModel.class));
            if(getUserModel()!=null)
                System.out.println(getUserModel().toString());
            else {
                System.out.println("haven't login");
            }
            //userid fragment 处理
            userID=userModel.getUserID();
            nickname=userModel.getNickname();
            imageID=userModel.getImageID();
            password=userModel.getPassword();
            getUserid();

            /**==============zpp============**/
            getPassword();
            getNickname();
            getImageID();
            /**==============zpp============**/
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(getApplicationContext(),"您还没有添加课程的权限噢!",false);

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_all_courses, R.id.nav_my_info,
                R.id.nav_footprint, R.id.nav_contact)
                .setDrawerLayout(drawer)
                .build();
        //点击导航栏对应部分 加载的东西
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




    }
    //两个fragment获取user id
    public String getUserid()
    {

        if(userModel!=null) {
            return userModel.getUserID();
        }
        else
        {
            return null;}
    }

    /**==============zpp============**/
    public String getPassword()
    {

        if(userModel!=null) {
            return userModel.getPassword();
        }
        else
        {
            return null;}
    }

    public String getNickname()
    {

        if(userModel!=null) {
            return userModel.getNickname();
        }
        else
        {
            return null;}
    }

    public int getImageID()
    {

        if(userModel!=null) {
            return userModel.getImageID();
        }
        else
        {
            return 0;}
    }

    /**==============zpp============**/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
