package com.bamboo.grow;

import com.bamboo.practice.gogo.InvocationHandlerImpl;
import com.bamboo.practice.gogo.StudyInvocationHandlerImpl;
import com.bamboo.service.Study;
import com.bamboo.service.Subject;
import com.bamboo.service.leran.RealStudy;
import com.bamboo.service.leran.RealSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wls
 * @version v1.0
 * @date 2018/10/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyTest {
    @Test
    public void ProxyTest(){
        //代理的真实对象
        Subject realSubject = new RealSubject();

        /**
         * InvocationHandlerImpl 实现了 InvocationHandler 接口，并能实现方法调用从代理类到委托类的分派转发
         * 其内部通常包含指向委托类实例的引用，用于真正执行分派转发过来的方法调用.
         * 即：要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法
         */
        InvocationHandler handler = new InvocationHandlerImpl(realSubject);


        ClassLoader loader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();
        /**
         * 该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
         */
        Subject subject = (Subject) Proxy.newProxyInstance(loader, interfaces, handler);

        System.out.println("动态代理对象的类型："+subject.getClass().getName());

        String hello = subject.SayHello("jiankunking");
        System.out.println(hello);
//        String goodbye = subject.SayGoodBye();
//        System.out.println(goodbye);

    }
    @Test
    public void ProxyTest1(){
        RealStudy realStudy = new RealStudy();
        StudyInvocationHandlerImpl studyInvocationHandler = new StudyInvocationHandlerImpl(realStudy);
        ClassLoader classLoader = realStudy.getClass().getClassLoader();
        Class<?>[] interfaces = realStudy.getClass().getInterfaces();
        Study study = (Study) Proxy.newProxyInstance(classLoader, interfaces, studyInvocationHandler);
        String s = study.go("学习动态代理");
        System.out.println(s);
        //System.out.println(study.sleep());
    }
}
