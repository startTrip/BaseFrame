package com.mihua.frameproject.thread;

/**
 * <pre>
 *     author : wang
 *     e-mail : wm@tairunmh.com
 *     time   : 2018/08/03
 *     desc   :
 * </pre>
 */
public class NoVisibility {


    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread{


        @Override
        public void run() {

            while (!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args){

        new ReaderThread().start();
        number = 42;
        ready = true;

    }


}
