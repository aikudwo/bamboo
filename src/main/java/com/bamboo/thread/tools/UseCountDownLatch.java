package com.bamboo.thread.tools;

import java.util.concurrent.CountDownLatch;

/**
 * @author wls
 * @date 2020-04-22 15:14
 */
public class UseCountDownLatch {

    //创建
    static CountDownLatch latch = new CountDownLatch(6);

    private static class oneThread implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread_"+Thread.currentThread().getId()
                    +" ready init work......");
            latch.countDown();
            for(int i =0;i<2;i++) {
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +" ........continue do its work");
            }
        }
    }

    /*业务线程等待latch的计数器为0完成*/
    private static class busThread implements Runnable{
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i =0;i<3;i++) {
                System.out.println("BusiThread_"+Thread.currentThread().getId()
                        +" do business-----");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        //创建一个线程扣减两次
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +" ready init work step 1st......");
                latch.countDown();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread_"+Thread.currentThread().getId()
                        +" ready init work step 2nd......");
                latch.countDown();
            }
        }).start();
        new Thread(new busThread()).start();

        //创建4次线程，扣减4次
        for (int i = 0; i < 4; i++) {
            new Thread(new oneThread()).start();
        }

        //设置主线程等待扣减数为0时执行
        latch.await();
    }
}
