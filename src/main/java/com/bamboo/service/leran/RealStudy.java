package com.bamboo.service.leran;

import com.bamboo.service.Study;

/**
 * @author wls
 * @version v1.0
 * @date 2018/10/30
 */
public class RealStudy implements Study {
    @Override
    public String go(String s) {
        return "学习" + s;
    }

    @Override
    public String sleep() {
        return "别学了，睡觉吧";
    }
}
