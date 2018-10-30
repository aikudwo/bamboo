package com.bamboo.service.leran;

import com.bamboo.service.Subject;

/**
 * @author wls
 * @version v1.0
 * @date 2018/10/30
 */
public class RealSubject implements Subject {
    /**
     * 你好
     *
     * @param name
     * @return
     */
    public String SayHello(String name)
    {
        return "hello " + name;
    }

    /**
     * 再见
     *
     * @return
     */
    public String SayGoodBye()
    {
        return " good bye ";
    }
}
