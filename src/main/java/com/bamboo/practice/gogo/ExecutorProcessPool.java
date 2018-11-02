package com.bamboo.practice.gogo;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author wls
 * @version v1.0
 * @date 2018/11/1
 */
public class ExecutorProcessPool {

    //线程池接口
    private ExecutorService executor;
    //实例化当前类
    private static ExecutorProcessPool pool = new ExecutorProcessPool();
    //实例化当前类
    private static ExecutorServiceFactory factory = ExecutorServiceFactory.getInstance();

    /**
     * Creates a new instance of ExecutorProcessPool
     */
    private ExecutorProcessPool() {
        executor = ExecutorServiceFactory.getInstance().createCachedThreadPool();
    }

    /**
     * 获取工厂类
     * @return
     */
    public static ExecutorServiceFactory getFactory(){
        return factory;
    }

    /**
     * @Function: 获取 ExecutorProcessPool
     */
    public static ExecutorProcessPool getInstance() {
        return pool;
    }

    /**
     * @Function:  关闭线程池，这里要说明的是：调用关闭线程池方法后，线程池会执行完队列中的所有任务才退出
     */
    public void shutdown(){
        executor.shutdown();
    }

    /**
     * @Function: 提交任务到线程池，可以接收线程返回值 - Future模式
     */
    public Future<?> submit(Runnable task) {
        return executor.submit(task);
    }
    public Future<?> submit(Callable<?> task) {
        return executor.submit(task);
    }

    /**
     * @Function: 直接提交任务到线程池，无返回值
     */
    public void execute(Runnable task){
        executor.execute(task);
    }
}

