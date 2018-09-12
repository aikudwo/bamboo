package com.bamboo.Controller;

import com.bamboo.aspect.MyLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AspectController {
    @MyLog
    @RequestMapping("/")
    public Object AspectTest(){
        int i = 0 ;
        int j = 0;
        int c = i/j;
        System.out.println("开始执行AspectTest:"+System.currentTimeMillis());
        return "666";
    }
}
