package com.mihua.frameproject.socket.socketserver;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/06/22
 *     desc   :
 * </pre>
 */
public class Test1 {

    public static void main(String[] args) {

        startClient();
    }

    // 启动客户端
    public static void startClient() {

        Log.d("client", "启动客户端");
        Log.d("result", Thread.currentThread().toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Log.d("result", "客户端线程" + Thread.currentThread().getState().toString());
                OutputStream outputStream = null;
                BufferedWriter bufferedWriter = null;
                BufferedReader bufferedReader = null;
                try {
                    Socket socket = new Socket("127.0.0.1", 6300);
                    Log.d("result", "客户端线程" + Thread.currentThread().toString());
                    Log.d("result", socket.getInetAddress().getHostName() + "," + socket.getInetAddress().getHostAddress());
                    outputStream = socket.getOutputStream();
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String s = "我是客户端";
                    bufferedWriter.write(s, 0, s.length());
                    bufferedWriter.flush();
                    String line = null;
                    while ((line = bufferedReader.readLine())!=null){
                        Log.d("result","客户端收到的消息"+(new StringBuffer().append(line)).toString());
                    }
                } catch (IOException e) {
                    Log.d("message",e.getMessage());
                    e.printStackTrace();

                } finally {
                    try {
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if(bufferedReader!=null){
                            bufferedReader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Log.d("result", thread.getState().toString());
        thread.start();

    }
}
