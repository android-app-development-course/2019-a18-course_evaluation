package com.scottmangiapane.courseevaluation.util.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by gdszm on 2018/12/12.
 */

public class StreamUtil {
    /**
     * 获取的读取流转为String返回
     *
     * @param
     */
    public static String stremToString(InputStream in) {
        String result="";
        try {
            //创建一个字节数组写入流
            ByteArrayOutputStream out=new ByteArrayOutputStream();

            byte[] buffer=new byte[1024];
            int length=0;
            while((length=in.read(buffer))!=-1){  //如果返回-1 则表示数据读取完成了。
                out.write(buffer,0,length);//写入数据
                out.flush();
            }
            result=out.toString();//写入流转为字符串
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
