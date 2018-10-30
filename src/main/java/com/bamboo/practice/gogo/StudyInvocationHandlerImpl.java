package com.bamboo.practice.gogo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wls
 * @version v1.0
 * @date 2018/10/30
 */
public class StudyInvocationHandlerImpl implements InvocationHandler {

    /**
     * 真实的对象
     */
    private Object study;

    /**
     * 构造方法
     */
    public StudyInvocationHandlerImpl (Object study){
        this.study = study;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作
        System.out.println("在调用之前，xuexi");

        System.out.println("Method:" + method);

        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object returnValue = method.invoke(study, args);

        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("在调用之后，我要干点啥呢？");

        return returnValue;
    }
}
