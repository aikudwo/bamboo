package com.bamboo.thread;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wls
 * @date 2020-05-09 10:46
 */
public class zhongjiang {

    private final static Integer front = 5;
    private final static Integer back = 2;
    private final static Integer[] template = {3,9,17,18,20,1,9};
    private final static List<Integer> templateList =Arrays.asList(template);

    public static void main(String[] args) {
        zhongjiang();
    }

    private static void zhongjiang(){
        AtomicLong count = new AtomicLong(0L);
        //前区list集合
        List<Integer> list1 = new ArrayList<>();
        //后区list集合
        List<Integer> list2 = new ArrayList<>();
        List<Integer> results = new ArrayList<>();
        while (!checkDiffrent(templateList,results)){
            results.clear();
            list1.clear();
            list2.clear();
            //前区1-35，随机五个不重复
            while (list1.size() < front) {
                int a = (int) (Math.random() * 35 + 1);
                if (!list1.contains(a)) {
                    list1.add(a);
                }
            }
            //按升序排列
            Collections.sort(list1);
            //后区1-12随机2个不重复
            while (list2.size() < back) {
                int b = (int) (Math.random() * 12+1);
                if (!list2.contains(b)){
                    list2.add(b);
                }
            }
            //按升序排列
            Collections.sort(list2);
            results.addAll(list1);
            results.addAll(list2);

            count.addAndGet(1) ;
            System.out.println("第" + count + "尝试");
        }
        System.out.println("共经历了 ：" +count + "次，才成功");
        System.out.println("最终结果是 ：" +results);
    }

    private static boolean checkDiffrent(List<Integer> list, List<Integer> list1) {

        if(list.equals(list1)){
            return true;
        }else {
            return false;
        }
    }
}
