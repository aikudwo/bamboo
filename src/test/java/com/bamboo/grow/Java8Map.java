package com.bamboo.grow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Java8Map {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *java8map遍历
     */
    @Test
    public void  Mapforeach(){
        HashMap<String, String> map = cmap();
        map.forEach((k,v) -> System.out.println(k+"++++"+v));
    }

    /**
     * getOrDefault
     * 作用是按照给定的key查询Map中对应的value，如果没有找到则返回defaultValue。
     * 使用该方法程序员可以省去查询指定键值是否存在的麻烦．
     */
    @Test
    public void getOrDefault(){
        HashMap<String, String> map = cmap();
        String orDefault = map.getOrDefault("4", "99999");
        System.out.println(orDefault);
    }

    /**
     * 作用是只有在不存在key值的映射或映射值为null时，才将value指定的值放入到Map中，
     * 否则不对Map做更改．该方法将条件判断和赋值合二为一，使用起来更加方便．
     */
    @Test
    public void putIfAbsent(){
        HashMap<String, String> cmap = cmap();
        cmap.putIfAbsent("2","88888");
        cmap.putIfAbsent("4","777777");
        cmap.forEach((k,v)-> System.out.println(k+"+++++++"+v));
    }

    /**
     * 只有在当前Map中key正好映射到value时才删除该映射，否则什么也不做
     */
    @Test
    public void remove(){
        HashMap<String, String> cmap = cmap();
        boolean remove = cmap.remove("2", "455");
        System.out.println(remove);
        cmap.forEach((k,v)-> System.out.println(k+"+++++++"+v));
    }

    /**
     * replace(K key, V value)，只有在当前Map中key的映射存在时才用value去替换原来的值，否则什么也不做．
     * replace(K key, V oldValue, V newValue)，只有在当前Map中key的映射存在且等于oldValue时才用newValue去替换原来的值，否则什么也不做．
     */
    @Test
    public void replace(){
        HashMap<String, String> cmap = cmap();
        cmap.replace("4", "first");
        cmap.replace("1","first","one");
        cmap.forEach((k,v)-> System.out.println(k+"+++++++"+v));
    }

    /**
     * 替换全部
     */
    @Test
    public void replaceAll(){
        HashMap<String, String> cmap = cmap();
        cmap.replaceAll((k,v)->v.toUpperCase());
        cmap.forEach((k,v)-> System.out.println(k+"+++++++"+v));
    }


    @Test
    public void merge(){
        HashMap<String, String> map = cmap();
        String first = map.merge("1", "first", (v1, v2) -> v1 + v2);
        map.forEach((k,v)-> System.out.println(k+"+++++++"+v));
    }

    /**
     * 如果Map中key对应的映射不存在或者为null，则将value（不能是null）关联到key上；
     * 否则执行remappingFunction，如果执行结果非null则用该结果跟key关联，否则在Map中删除key的映射．
     * @return
     */
    private static final HashMap<String,String> cmap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("1", null);
        map.put("2", "two");
        map.put("3", "three");
        return  map;
    }
}
