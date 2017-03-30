package com.mihua.code.http.volley.request;

import com.mihua.code.http.volley.listener.IHttpListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/17
 */
public class JsonHttpService implements IHttpService {

    private String mUrl;
    private byte[] mByte;
    private IHttpListener mIHttpListener;

    // 设置url
    @Override
    public void setUrl(String url) {
        mUrl = url;
    }

    // 执行请求
    @Override
    public void execute() {

        try {
            URL url = new URL(mUrl);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setConnectTimeout(30);
            httpUrlConnection.setDoOutput(true);
            OutputStream outputStream = httpUrlConnection.getOutputStream();
            outputStream.write(mByte);

            int responseCode = httpUrlConnection.getResponseCode();
            // 如果请求成功
            if(responseCode==200){
                InputStream inputStream = httpUrlConnection.getInputStream();
                if (mIHttpListener != null) {

                    mIHttpListener.onSuccess(inputStream);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取回调
    @Override
    public void setHttpCallBack(IHttpListener iHttpListener) {

        mIHttpListener = iHttpListener;
    }

    @Override
    public void setParam(byte[] b) {
        mByte = b;
    }
}
