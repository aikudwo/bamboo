package com.bamboo.grow;

import com.bamboo.model.Company;
import com.bamboo.model.ReflectionDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wls
 * @version v1.0
 * @date 2018/10/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Reflection {
    /**
     * 创建clasa 的三种方式
     */
    @Test
    public void Test1(){
        Class c = null;
        Class c1 = null;
        Class c2 = null;
        try {
            c = Class.forName("com.bamboo.model.Company");
        }catch (ClassNotFoundException e){

        }
        Company company = new Company();
        Object object = company;
        c1 = object.getClass();

        c2 = company.getClass();

        System.out.println(c);
        System.out.println(c1);
        System.out.println(c2);

        try {
            Object o = c2.newInstance();
            System.out.println("company : " + o);
        }catch (Exception e){

        }
    }

    /**
     *  获取类中所有属性
     */
    @Test
    public void Test2(){
        ReflectionDO reflectionDO = new ReflectionDO();
        reflectionDO.setId(1);
        reflectionDO.setName("王上");
        reflectionDO.setType("11");
        Class<? extends ReflectionDO> aClass = reflectionDO.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Field[] fields = aClass.getFields();
        for (Field declaredField : declaredFields) {
            System.out.println("属性名称"+ declaredField.getName() + "属性类型  ：" + declaredField.getType());
        }

        for (Field field : fields) {
            System.out.println("公共属性名称"+ field.getName() + "公共属性类型  ：" + field.getType());
        }

        try {
            Field field = aClass.getField("王上");
            System.out.println( field);
        }catch (NoSuchFieldException e){

        }

    }

    /**
     * 获取所有方法
     */
    @Test
    public void Test3(){
        ReflectionDO reflectionDO = new ReflectionDO();
        reflectionDO.setId(1);
        reflectionDO.setName("王上");
        reflectionDO.setType("11");
        Class<? extends ReflectionDO> aClass = reflectionDO.getClass();

        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println("方法名称=="+m.getName());
            System.out.println("方法返回值类型=="+m.getReturnType());
            System.out.println("方法修饰符标号=="+m.getModifiers());
            //System.out.println("方法修饰符=="+ Modifier.toString(m.getModifiers()));
            System.out.println("方法的注解信息为");
            Annotation[] annotations = m.getDeclaredAnnotations();
            for (Annotation a:annotations
            ) {
                System.out.print(a.toString());
            }
            System.out.println("方法的参数列表为");
            Class[] cl1=m.getParameterTypes();
            for (Class cll:cl1
            ) {
                System.out.print(" "+cll.getName());
            }

        }
    }

    @Test
    public void Test4(){
        ReflectionDO reflectionDO = new ReflectionDO();
        reflectionDO.setId(1);
        reflectionDO.setName("王上");
        reflectionDO.setType("11");
        Class<? extends ReflectionDO> aClass = reflectionDO.getClass();
        try {
            //获取对应方法，传入方法名和方法参数,方法参数为Class数组
            Method setName = aClass.getMethod("setName", String.class);
            //执行该方法,第一个参数为执行该对象的对象，
            // 第二个参数为方法的参数,方法参数为Object数组
            setName.invoke(reflectionDO,"悲惨的时间");
            setName.invoke(reflectionDO,new Object[]{"悲惨的世界"});

            Method m2=aClass.getMethod("getName",null);
            System.out.println(m2.invoke(reflectionDO,new Object[0]));

            Method m3=aClass.getMethod("aa",new Class[]{int.class,String.class,String.class});
            m3.invoke(reflectionDO,new Object[]{6,"哈哈哈","嘿嘿"});
        }catch (Exception e){

        }


    }

    /**
     * 获取构造方法
     */
    @Test
    public void Test5(){
        try {
            Class<?> aClass = Class.forName("com.bamboo.model.ReflectionDO");
            Constructor<?> constructor2 = aClass.getConstructor();
            ReflectionDO o = (ReflectionDO) constructor2.newInstance();
            System.out.println(o.toString());
            //获取指定类型的构造方法
            Constructor<?> constructor1 = aClass.getConstructor(String.class);
            System.out.println(constructor1);
            //获取所有public修饰的构造方法
            Constructor<?>[] constructors = aClass.getConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }
        }catch (Exception e){

        }

    }

    /**
     * 创建实列
     */
    @Test
    public void Test6(){
        try {
            Class<?> aClass = Class.forName("com.bamboo.model.ReflectionDO");
            Object o1 = aClass.newInstance();
            System.out.println("01 " + o1.toString());
            Constructor<?> constructor = aClass.getConstructor(String.class, String.class);
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(parameterType);
            }
            //运行构造函数
            Object o = constructor.newInstance("66666", "这就是伟大的反射");
            System.out.println(o.toString());
        }catch (Exception e){

        }

    }
}
