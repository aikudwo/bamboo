package com.bamboo.thread.forkjoin;

import java.util.Random;

/**
 * @author wls
 * @date 2020-04-16 17:49
 */
public class MakeArray {

    //数组长度
    public static final int ARRAY_LENGTH  = 300000000;
    public final static int THRESHOLD = 47;

    public static int[] makeArray() {

        //new一个随机数发生器
        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for(int i=0;i<ARRAY_LENGTH;i++){
            //用随机数填充数组
            result[i] =  r.nextInt(ARRAY_LENGTH*3);
        }
        return result;

    }
}