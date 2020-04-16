package com.bamboo.thread.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author wls
 * @date 2020-04-16 15:17
 * 实现连接池
 */
public class DBPool {
    /*容器，存放连接*/
    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initialSize){
        if(initialSize >0){
            for (int i = 0; i < initialSize; i++) {
                //创建一个连接放在容器的尾部
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    /*释放连接,通知其他的等待连接的线程*/
    public void releaseConnection(Connection connection){
        if(connection !=null){
            synchronized (pool){
                //将连接放回池中
                pool.addLast(connection);
                //通知其他线程
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取连接
     * 在mills时间内获取不到，则返回null
     * @param mills
     * @return
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            //用不超时
            if(mills <= 0){
                //如果连接池中没有可连接的进入等待
                while (pool.isEmpty()){
                    pool.wait();
                }
                //否则，则返回第一个连接
                return pool.removeFirst();
            }else {
                /*超时时刻*/
                long future = System.currentTimeMillis()+mills;
                /*等待时长*/
                long remaining = mills;
                while(pool.isEmpty()&&remaining>0){
                    pool.wait(remaining);
                    /*唤醒一次，重新计算等待时长*/
                    remaining = future-System.currentTimeMillis();
                }
                Connection connection = null;
                if(!pool.isEmpty()){
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }
}
