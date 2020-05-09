package com.bamboo.thread.tools;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wls
 * @date 2020-04-22 15:40
 */
public class UseCyclicBarrier {
    //将汇总线程放入
    static CyclicBarrier barrier = new CyclicBarrier(4,new sumThread());

    static ConcurrentHashMap<String,Long> map = new ConcurrentHashMap();

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(new subThread()).start();
        }
    }

    //编写汇总线程
    private static class sumThread implements Runnable{
        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String,Long> workResult : map.entrySet()){
                result.append("["+workResult.getValue()+"]");
            }
            System.out.println(" the result = "+ result);
            System.out.println("do other business........");
        }
    }

    //编写处理线程
    private static class subThread implements Runnable{
        @Override
        public void run() {
            try {
                //将线程放入map中，方便汇总
                long id = Thread.currentThread().getId();
                map.put(Thread.currentThread().getId()+"",id);
                System.out.println("Thread_"+id+" ....do something ");
                barrier.await();
                Thread.sleep(1000+id);
                System.out.println("Thread_"+id+" ....do its business ");
                barrier.await();
            }catch (Exception e){

            }
        }
    }
}
