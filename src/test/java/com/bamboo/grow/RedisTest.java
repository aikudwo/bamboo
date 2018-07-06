package com.bamboo.grow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void contextLoads() {

        stringRedisTemplate.opsForValue().set("dffd","ffsdfff",6000, TimeUnit.SECONDS);
        String dffd = stringRedisTemplate.opsForValue().get("dffd");
        stringRedisTemplate.delete("dffd");
    }
}
