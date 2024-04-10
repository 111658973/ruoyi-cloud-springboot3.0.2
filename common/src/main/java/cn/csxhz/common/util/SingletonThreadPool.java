package cn.csxhz.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Cc
 */
public class SingletonThreadPool {

    private static volatile SingletonThreadPool instance = null;

    // 线程池配置常量
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static final long KEEP_ALIVE_TIME = 60L;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>(100);

    private final ThreadPoolExecutor executor;

    // 使用SLF4J记录日志
    private static final Logger logger = LoggerFactory.getLogger(SingletonThreadPool.class);

    // 私有构造函数
    private SingletonThreadPool() {
        this.executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TIME_UNIT,
                WORK_QUEUE,
                new ThreadFactoryWithExceptionHandler()
        );
    }

    // 公共的静态方法用于获取单例
    public static SingletonThreadPool getInstance() {
        if (instance == null) {
            synchronized (SingletonThreadPool.class) {
                if (instance == null) {
                    instance = new SingletonThreadPool();
                }
            }
        }
        return instance;
    }

    // 提供向线程池提交任务的方法
    public void submit(Runnable task) {
        executor.submit(task);
    }

    // 自定义线程工厂，为线程设置统一的异常处理逻辑
    private static class ThreadFactoryWithExceptionHandler implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        ThreadFactoryWithExceptionHandler() {
            this.group = Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            t.setUncaughtExceptionHandler((thread, e) -> {
                // 使用SLF4J处理未捕获的异常日志
                logger.error("Unhandled exception in thread: {}", thread.getName(), e);
            });
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
