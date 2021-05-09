package com.zjh.demo.redis.service.impl;

import com.zjh.demo.redis.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service("myRedisService")
public class RedisServiceImpl implements RedisService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);
    //
    private static final Converter<String, Boolean> STRING_TO_BOOLEAN = new StringToBooleanConverter();
    private static final Converter<String, Boolean> INTEGER_TO_BOOLEAN = new IntegerToBooleanConverter();

    private static class IntegerToBooleanConverter implements Converter<String, Boolean> {
        @Override
        public Boolean convert(String result) {
            return StringUtils.equalsIgnoreCase(result, "1");
        }
    }

    private static class StringToBooleanConverter implements Converter<String, Boolean> {
        @Override
        public Boolean convert(String result) {
            return StringUtils.equalsIgnoreCase(result, "OK");
        }
    }

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void setString(String key, String value, long expiredSeconds) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return;
        }
        try {
            redisTemplate.opsForValue().set(key, value, expiredSeconds);
        }catch (Exception e){
            LOGGER.error("setString error key:{} value:{} expired:{}", key, value, expiredSeconds, e);
        }
    }

    @Override
    public void setString(String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            LOGGER.error("setString error key:{} value:{}", key, value, e);
        }
    }

    @Override
    public String getString(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String value = redisTemplate.opsForValue().get(key);
        LOGGER.info("get by redisTemplate, key={}, value={}", key, value);
        return value;
    }

    @Override
    public void set(String key, String value, long expiredSeconds) {
        redisTemplate.execute((RedisCallback<Boolean>) connection->{
            RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
            // 按照redisTemplate的序列化方式来将key和value进行序列化
            byte[] serializeKey = stringSerializer.serialize(key);
            byte[] serializeValue = stringSerializer.serialize(value);
            connection.set(serializeKey,serializeValue);
            connection.expire(serializeKey,expiredSeconds);
            return true;
        });
    }

    @Override
    public String get(String key) {
        return redisTemplate.execute((RedisCallback<String>) connection->{
            RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
            // 先序列化获取key对应的value
            byte[] bytes = connection.get(stringSerializer.serialize(key));
            // 再将value反序列化为看得懂的值返回
            return stringSerializer.deserialize(bytes);
        });
    }

    @Override
    public void expire(String key, long expire) {

    }

    @Override
    public void expire(String key, long expire, TimeUnit timeUnit) {

    }
}
