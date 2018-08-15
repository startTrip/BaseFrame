package com.mihua.frameproject.socket.socketbroadcast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/05/28
 *     desc   :
 * </pre>
 */
public class SocketServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(6300);
            System.out.println("等待socket连接");
            Socket socket = serverSocket.accept();

            System.out.println("socket连接成功");

            readWriteDataToClient(socket);
//            SocketTask socketTask = new SocketTask(socket);
//            ThreadPoolManager.getInstance().execute(new FutureTask(socketTask,null));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

    private static void readWriteDataToClient(Socket socket) {

        InputStream inputStream=null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            inputStream= socket.getInputStream();
            outputStream = socket.getOutputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String content = null;
            while ((content = bufferedReader.readLine())!=null){
                System.out.println("收到数据"+content);

            }


            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String s1 = "我是TcpSocket服务端\r\n";
            bufferedWriter.write(s1,0,s1.length());
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if(outputStream!=null){
                    outputStream.close();
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
