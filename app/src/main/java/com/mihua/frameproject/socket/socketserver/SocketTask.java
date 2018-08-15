package com.mihua.frameproject.socket.socketserver;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Project: UpLoadFile
 * Author: wm
 * Data:   2017/3/21
 */

public class SocketTask implements Runnable {

    public Socket mSocket;
    public StringBuffer mStringBuffer;

    public SocketTask(Socket socket) {
        mSocket = socket;
        mStringBuffer = new StringBuffer();
        System.out.println("11");
    }

    @Override
    public void run() {

        String path = Environment.getExternalStorageDirectory()+File.separator+"sock1.txt";
//        Log.d("result",path);
        File file = new File(path);
        System.out.println("11");
        // 执行你的请求
        InputStream inputStream= null;
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inputStream = mSocket.getInputStream();
             bufferedInputStream= new BufferedInputStream(inputStream);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int i;
            while ((i=bufferedInputStream.read(b))!=-1){
                System.out.println("22");
                byteArrayOutputStream.write(b,0,b.length);
                byteArrayOutputStream.flush();
            }
            String string = byteArrayOutputStream.toString("UTF-8");
//            fileWriter = new FileWriter(file,true);
//            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            bufferedWriter = new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream()));
//            String line = null;
//
//            while ((line = bufferedReader.readLine())!=null){
//                fileWriter.write(line+"\r\n");
////                String string = "已收到信息";
////                bufferedWriter.write(string);
//                mStringBuffer.append(line);
//            }
            System.out.println("写入成功\r\n"+string);


        } catch (IOException e) {
//            Log.d("message",e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if(fileWriter!=null){
                    fileWriter.close();
                }
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
                if(bufferedWriter!=null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
