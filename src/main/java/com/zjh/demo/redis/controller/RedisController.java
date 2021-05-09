package com.zjh.demo.redis.controller;

import com.zjh.demo.redis.service.RedisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/services/redis")
public class RedisController {


    @Resource(name = "myRedisService")
    private RedisService redisService;

    @RequestMapping("/testSet")
    public void testSet(String key,String value,long expireSeconds){
       redisService.set(key,value,expireSeconds);
    }

    @RequestMapping("/testGet")
    public String testGet(String key){
        return redisService.get(key);
//        System.out.println("你好帅");
//        return "你好帅";
    }
}
