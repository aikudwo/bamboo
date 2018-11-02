package com.bamboo.practice.gogo;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * @author wls
 * @version v1.0
 * @date 2018/10/31
 */
public class ThreadTest implements Runnable{
    /**
     * 多线程实现购票系统（最简单版）
     */
    private int count = 5;

    public void run() {
        for (int i = 0; i < 10; ++i) {
            sale();
        }
    }

    public synchronized void sale() {
        if (count > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(count--);
        }
    }

    public static void main(String[] args) {
       /* ThreadTest he = new ThreadTest();
        Thread h1 = new Thread(he);
        Thread h2 = new Thread(he);
        Thread h3 = new Thread(he);
        Thread h4 = new Thread(he);
        h1.start();
        h2.start();
        h3.start();
        h4.start();*/


        ExecutorService executorService1 = Executors.newFixedThreadPool(1);
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        //for循环添加多个线程 -
        for (int i = 0; i < 200; i++) {
            executorService.execute(new ExcuteTask2(i+""));
            //Future<?> future = executorService1.submit(new ExcuteTask2(i + ""),i);
            try {
                //如果接收线程返回值，future.get() 会阻塞，如果这样写就是一个线程一个线程执行。所以非特殊情况不建议使用接收返回值的。
                //System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        /*//获取实例
        ExecutorProcessPool pool = ExecutorProcessPool.getInstance();

        //for循环添加多个线程 -
        for (int i = 0; i < 200; i++) {
            Future<?> future = pool.submit(new ExcuteTask1(i + ""));
            try {
                //如果接收线程返回值，future.get() 会阻塞，如果这样写就是一个线程一个线程执行。所以非特殊情况不建议使用接收返回值的。
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //没有返回值。可以执行任务，但无法判断任务是否成功完成。
        //execute(Runnable x);

        //for (int i = 0; i < 200; i++) {
        //  pool.execute(new ExcuteTask2(i + ""));
        //}

        // 关闭线程池，如果是需要长期运行的线程池，不用调用该方法。
        // 监听程序退出的时候最好执行一下。
        pool.shutdown();
    }

    *//**
         * 执行任务1，实现Callable方式
         *//*
    static class ExcuteTask1 implements Callable<String> {
        private String taskName;

        public ExcuteTask1(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String call() throws Exception {
            try {
                // Java 6/7最佳的休眠方法为TimeUnit.MILLISECONDS.sleep(100);
                // 最好不要用 Thread.sleep(100);
                // 1000毫秒以内的随机数，模拟业务逻辑处理
                TimeUnit.MILLISECONDS.sleep((int) (Math.random() * 1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("-------------这里执行业务逻辑，Callable TaskName = " + taskName + "-------------");
            return ">>>>>>>>>>>>>线程返回值，Callable TaskName = " + taskName + "<<<<<<<<<<<<<<";
        }
    }*/
    }
        /**
         * 执行任务2，实现Runable方式
         */
        static class ExcuteTask2 implements Runnable {
            private String taskName;

            public ExcuteTask2(String taskName) {
                this.taskName = taskName;
            }

            @Override
            public void run() {
                try {
                    // 1000毫秒以内的随机数，模拟业务逻辑处理
                    TimeUnit.MILLISECONDS.sleep((int) (Math.random() * 1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("-------------这里执行业务逻辑，Runnable TaskName = " + taskName + "-------------");
            }
        }
        static class ExcuteTask1 implements Callable<String> {
            private String taskName;

            public ExcuteTask1(String taskName) {
                this.taskName = taskName;
            }

            @Override
            public String call() throws Exception {
                try {
                    TimeUnit.MILLISECONDS.sleep((int) (Math.random() * 1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("-------------这里执行业务逻辑，Runnable TaskName = " + taskName + "-------------");
                return ">>>>>>>>>>>>>线程返回值，Callable TaskName = " + taskName + "<<<<<<<<<<<<<<";
            }
        }

}
