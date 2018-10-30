package com.bamboo.service;

/**
 * @author wls
 * @version v1.0
 * @date 2018/10/30
 */
public interface Subject {
     /**
      * 你好
      *
      * @param name
      * @return
      */
     public String SayHello(String name);

     /**
      * 再见
      *
      * @return
      */
     public String SayGoodBye();
}
