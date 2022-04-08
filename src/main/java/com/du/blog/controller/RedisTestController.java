package com.du.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author DU425
 * @Date 2022/4/8 12:36
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public String testRedis(){
        redisTemplate.opsForValue().set("name","jane");
        return (String) redisTemplate.opsForValue().get("name");
    }

}
