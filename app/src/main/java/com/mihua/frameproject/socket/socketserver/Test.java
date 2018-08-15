package com.mihua.frameproject.socket.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2017/05/26
 *     desc   :
 * </pre>
 */
public class Test {

    private static int mI = 0;


    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(6300);
            System.out.println("等待socket连接");
            Socket socket = serverSocket.accept();

            System.out.println((mI++)+"个socket连接成功");

//            SocketTask socketTask = new SocketTask(socket);
//            ThreadPoolManager.getInstance().execute(new FutureTask(socketTask,null));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

}
