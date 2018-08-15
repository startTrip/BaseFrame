package com.mihua.frameproject.socket.socketserver;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Project: UpLoadFile
 * Author: wm
 * Data:   2017/3/21
 */
public class ThreadPoolManager {

    private static ThreadPoolManager mThreadPoolManager;
    private LinkedBlockingQueue<FutureTask> mLinkedBlockingQueue;
    private final ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager(){
        // 队列
        mLinkedBlockingQueue = new LinkedBlockingQueue<>(10);
        // 线程池维护
        mThreadPoolExecutor = new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
//                    Log.d("result","线程被拒绝");
                    // 被拒绝以后重新放进去
                    mLinkedBlockingQueue.put(new FutureTask(r,null));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        mThreadPoolExecutor.execute(mRunnable);
    }

    public void execute(FutureTask futureTask){

//        Log.d("result","111111111");
        if (futureTask != null) {
            try {
//                Log.d("result","将线程放入队列中");
                mLinkedBlockingQueue.put(futureTask);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 用于维护整个队列的 线程
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            while(true){
//                Log.d("result", "队列的线程"+Thread.currentThread().toString());
                try {
//                    Log.d("result","维护整个队列的线程");
                    // 从请求队列中得到任务
                    FutureTask futureTask = mLinkedBlockingQueue.take();

                    if (futureTask != null) {
                        // 执行一个请求
//                        Log.d("result","执行请求");
                        mThreadPoolExecutor.execute(futureTask);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static synchronized ThreadPoolManager getInstance(){

        if (mThreadPoolManager ==null){
            mThreadPoolManager = new ThreadPoolManager();
        }

        return mThreadPoolManager;
    }
}
