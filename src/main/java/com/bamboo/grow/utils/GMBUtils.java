package com.bamboo.grow.utils;


import org.apache.commons.lang.StringUtils;

/**
 * 写几个工具类，应对项目中频繁遇到的问题
 * 字符串去掉第一个逗号
 */
public class GMBUtils {

    public static String HandleString(String s1){
        //,1,2,3
        int count=0;
        StringBuilder sb = new StringBuilder();
        String[] split = s1.split(",");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
            if(StringUtils.isNotBlank(split[i])){

                if(i==count){
                    sb.append(split[i]);
                }else {
                    sb.append(","+split[i]);
                }
            }else{
                count++;
            }

        }
        return sb.toString();
    }

      /*  public static void main(String[] args){
        String s = ",1,,2,faf,dadfd";
            String s1 = GMBUtils.HandleString(s);
            System.out.println(s1);
        }*/
}

