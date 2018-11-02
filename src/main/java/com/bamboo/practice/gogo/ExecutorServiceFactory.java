package com.bamboo.practice.gogo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wls
 * @version v1.0
 * @date 2018/11/1
 */
public class ExecutorServiceFactory {

    // 实例化ExecutorServiceFactory
    private static ExecutorServiceFactory executorServiceFactory = new ExecutorServiceFactory();

    // 定时任务线程池
    private ExecutorService executorService;

    /**
     * 默认无参构造
     */
    private ExecutorServiceFactory() { }

    /**
     * @Function: 获取ExecutorServiceFactory
     */
    public static ExecutorServiceFactory getInstance() {
        return executorServiceFactory;
    }

    /**
     * @Function: 创建一个定长的线程池 - 它可安排在给定延迟后运行命令或者定期地执行
     */
    public ExecutorService createScheduledThreadPool() {
        // CPU个数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        // 创建
        executorService = Executors.newScheduledThreadPool(availableProcessors * 10, getThreadFactory());
        return executorService;
    }

    /**
     * @Function: 创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     *            Executor，以无界队列方式来运行该线程。（注意，如果因为在关闭前的执行期间出现失败而终止了此单个线程，
     *            那么如果需要，一个新线程将代替它执行后续的任务 ）。可保证顺序地执行各个任务，
     *            并且在任意给定的时间不会有多个线程是活动的。与其他等效的 newFixedThreadPool(1)
     *            不同，可保证无需重新配置此方法所返回的执行程序即可使用其他的线程。
     */
    public ExecutorService createSingleThreadExecutor() {
        // 创建
        executorService = Executors.newSingleThreadExecutor(getThreadFactory());
        return executorService;
    }

    /**
     * @Function: 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     *            对于执行很多短期异步任务的程序而言，这些线程池通常可提高程序性能。调用 execute将重用以前构造的线程（如果线程可用）。
     *            如果现有线程没有可用的，则创建一个新线程并添加到池中。 终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
     *            因此，长时间保持空闲的线程池不会使用任何资源。注意，可以使用 ThreadPoolExecutor
     *            构造方法创建具有类似属性但细节不同（例如超时参数）的线程池。
     */
    public ExecutorService createCachedThreadPool() {
        // 创建
        executorService = Executors.newCachedThreadPool(getThreadFactory());
        return executorService;
    }

    /**
     * @Function: 创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，
     *            如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。
     *            可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。在任意点，在大多数 nThreads
     *            线程会处于处理任务的活动状态。如果在所有线程处于活动状态时提交附加任务，则在有可用线程之前，附加任务将在队列中等待。
     *            如果在关闭前的执行期间由于失败而导致任何线程终止，那么一个新线程将代替它执行后续的任务（如果需要）。
     *            在某个线程被显式地关闭之前，池中的线程将一直存在。
     */
    public ExecutorService createFixedThreadPool(int count) {
        // 创建
        executorService = Executors.newFixedThreadPool(count, getThreadFactory());
        return executorService;
    }

    /**
     * @Function: 获取线程池工厂
     */
    private ThreadFactory getThreadFactory() {
        return new ThreadFactory() {
            // AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减
            AtomicInteger sn = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                // 安全管理器
                SecurityManager s = System.getSecurityManager();
                // 所有线程都隶属于一个线程组
                ThreadGroup group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
                Thread t = new Thread(group, r);
                t.setName("任务线程 - " + sn.incrementAndGet());
                return t;
            }
        };
    }
}

