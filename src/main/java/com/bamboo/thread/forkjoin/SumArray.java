package com.bamboo.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author wls
 * @date 2020-04-16 17:50
 * ForkJoin执行累加
 * Task 类具有返回值
 * action 没有返回值
 */
public class SumArray {
    private static class SumTask extends RecursiveTask<Integer> {
        /*阈值*/
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }


        @Override
        protected Integer compute() {
            /*任务的大小是否合适*/
            if(toIndex - fromIndex < THRESHOLD){
                int count = 0;
                for (int i = fromIndex; i < toIndex; i++) {
                    count = count + src[i];
                }
                return count;
            }else {
                int mid = (fromIndex+toIndex)/2;
                SumTask left = new SumTask(src,fromIndex,mid);
                SumTask right = new SumTask(src,mid+1,toIndex);
                invokeAll(left,right);
                return left.join()+right.join();
            }
        }
    }

    public static void main(String[] args) {
        int[] src = MakeArray.makeArray();
        /*new出池的实例*/
        ForkJoinPool pool = new ForkJoinPool();
        /*new出Task的实例*/
        SumTask innerFind = new SumTask(src,0,src.length-1);
        long start = System.currentTimeMillis();
        Integer invoke = pool.invoke(innerFind);
        System.out.println("Task is Running.....");
        System.out.println("The count is "+innerFind.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
