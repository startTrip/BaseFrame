package com.mihua.code.http.volley;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/17
 */
public class ThreadPoolManager {

    private static ThreadPoolManager mThreadPoolManager;
    private final LinkedBlockingQueue<FutureTask> mLinkedBlockingQueue;
    private final ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mLinkedBlockingQueue = new LinkedBlockingQueue(10);
        mThreadPoolExecutor = new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                executor.execute(r);
            }
        });
        mThreadPoolExecutor.execute(mRunnable);
    }

    // 线程执行 一直去获取 httpTask;
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            while (true){
                try {

                    FutureTask future = mLinkedBlockingQueue.take();
                    if (future != null) {
                        mThreadPoolExecutor.execute(future);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    };

    public void excute(FutureTask futureTask){

        if (futureTask != null) {
            try {
                mLinkedBlockingQueue.put(futureTask);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized  ThreadPoolManager getInstance(){

        if (mThreadPoolManager == null) {
            mThreadPoolManager = new ThreadPoolManager();
        }
        return  mThreadPoolManager;

    }

}
