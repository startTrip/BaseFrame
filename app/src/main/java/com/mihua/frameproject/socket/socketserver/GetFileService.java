package com.mihua.frameproject.socket.socketserver;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.FutureTask;

/**
 * Project: UpLoadFile
 * Author: wm
 * Data:   2017/3/21
 */
public class GetFileService extends IntentService{


    private int mI;

    public GetFileService() {

        super("get");
        mI = 0;
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GetFileService(String name) {
        super("");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("result","onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("result","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("result====",Thread.currentThread().toString());
        while (true){
            try {
                Log.d("result", "等待socket的线程"+Thread.currentThread().toString());
                ServerSocket serverSocket = new ServerSocket(6300);
                Log.d("result=","等待socket连接");
                Socket socket = serverSocket.accept();

                Log.d("result",(mI++)+"个socket连接成功");

                SocketTask socketTask = new SocketTask(socket);
                ThreadPoolManager.getInstance().execute(new FutureTask(socketTask,null));

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("result","客户端断开了");
            }
        }
    }
}
