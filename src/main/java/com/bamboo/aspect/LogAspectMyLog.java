package com.bamboo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * 通过切面实现注解
 */
@Component
@Aspect
public class LogAspectMyLog {
        @Pointcut("@annotation(com.bamboo.aspect.MyLog)")
        public void cut(){ }


    /**
     * 在目标方法被调用之前做增强处理,@Before只需要指定切入点表达式即可
     */
    @Before("cut()")
    public void before() {
        System.out.println("已经记录下操作日志@Before 方法执行前");
    }

    /**
     * 定制一个环绕通知
     *
     * 环绕通知,在目标方法完成前后做增强处理,环绕通知是最重要的通知类型,像事务,日志等都是环绕通知,注意编程中核心是一个ProceedingJoinPoint
     * @param joinPoint
     */
    @Around("cut()")
    public Object advice(ProceedingJoinPoint joinPoint){
        System.out.println("环绕通知之开始");
        try {
           return joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println();
            e.printStackTrace();
            return "菜鸡：又出bug";
        }
    }

    /**
     * 主要用来处理程序中未处理的异常,@AfterThrowing除了指定切入点表达式后，
     * 还可以指定一个throwing的返回值形参名,可以通过该形参名
     * @param returnVal
     */
   /*@AfterReturning(pointcut = "cut()",returning = "returnVal")
    public void afterReturen(Object returnVal){
       System.out.println("AOP AfterReturning Advice:" + returnVal);
   }*/

  /* @AfterThrowing(pointcut = "cut()",throwing = "error")
    public void throwPoint(JoinPoint joinPoint,Throwable error){
       System.out.println("只有出现错误才会打印哦" + error);
   }*/
}
