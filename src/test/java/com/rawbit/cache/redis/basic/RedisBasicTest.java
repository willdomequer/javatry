package com.rawbit.cache.redis.basic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public class RedisBasicTest {


    @Autowired
    private RedisCacheManager redisCacheManager;

    @Test
    public void testRedisSet() throws Exception {

        boolean result = redisCacheManager.set("test1", "hello spring-redis", 5000);

        System.out.println("access " + result);
    }
}
