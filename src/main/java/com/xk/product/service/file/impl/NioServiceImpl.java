package com.xk.product.service.file.impl;

import com.xk.product.service.file.NioService;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.io.InputStream;


import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 * @description: nio实现
 * @author: xk
 * @create: 2019-04-23 11:24
 **/
public class NioServiceImpl implements NioService {
    @Override
    public void doWork() {
        try {
            String urlStr="";
            String filePath="";
            File file = new File(filePath);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            InputStream inputStream = conn.getInputStream();
            SSLContext sSLContext =SSLContext.getInstance("TLS");
            if(sSLContext !=null){
                SSLSocketFactory sf = sSLContext.getSocketFactory();

            }
        } catch (Exception e) {
        }


    }
}
