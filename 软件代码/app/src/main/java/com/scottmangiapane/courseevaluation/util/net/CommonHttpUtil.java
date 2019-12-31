package com.scottmangiapane.courseevaluation.util.net;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by gdszm on 2018/12/12.
 */

public class CommonHttpUtil {

    //post方式登录
    public static void requestNet(final Handler handler,final String action,final String...args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //urlConnection请求服务器，验证
                try {
                    //1：url对象
                    URL url = new URL(URLProtocol.ROOT+"/"+ action);
                    System.out.print(url);
                    //2;url.openconnection
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    //3设置请求参数
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(10 * 1000);
                    //请求头的信息
                    String body ="";
                    for(int i=0; i<args.length; i++){
                        String key = args[i].substring(0,args[i].indexOf("="));
                        String value = args[i].substring(args[i].indexOf("=")+1,args[i].length());
                        if(i==0){
                            body=body+ key+"="+URLEncoder.encode(value, "UTF-8");
                        } else {
                            body=body+ "&"+key+"="+URLEncoder.encode(value, "UTF-8");
                        }
                    }
                    //String body = "cname=" + URLEncoder.encode(username) + "&cpwd=" + URLEncoder.encode(password);
                    conn.setRequestProperty("Content-Length", String.valueOf(body.length()));
                    conn.setRequestProperty("Cache-Control", "max-age=0");
//                    conn.setRequestProperty("Origin", URLProtocol.ROOT+":"+ URLProtocol.PORT);
                    conn.setRequestProperty("Origin", URLProtocol.ROOT);
                    conn.setRequestProperty("Accept-Charset", "utf-8");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + "utf-8");
                    //设置conn可以写请求的内容
                    conn.setDoOutput(true);
                    conn.getOutputStream().write(body.getBytes());

                    //4响应码
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String result = StreamUtil.stremToString(inputStream);
                        System.out.println("=====================服务器返回的信息：：" + result);
//                        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(result);
                        Message msg = Message.obtain();
                        msg.obj=result;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}