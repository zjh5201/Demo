package com.zjh.demo.redis.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {

    /**
     * 设置缓存，支持过期时间
     *
     * @param key
     *            key
     * @param value
     *            value
     * @param expiredSeconds
     *            expire，单位：秒
     * @return
     */
    void setString(String key, String value, long expiredSeconds);

    /**
     * 设置缓存，支持过期时间
     *
     * @param key
     *            key
     * @param value
     *            value
     * @return
     */
    void setString(String key, String value);

    /**
     *
     * @param key
     * @return
     */
    String getString(String key);
    /**
     * @author chenjiale
     * @description 序列号string
     * @Date 4:16 下午 2020/5/7
     * @param key:
     * @param value:
     * @param expiredSeconds:
     * @return: void
     */
    void set(String key, String value, long expiredSeconds);

    /**
     * 获取value
     *
     * @param key
     * @return value
     */
    String get(String key);

    /**
     * 设置缓存过期
     *
     * @param key
     *            key
     * @param expire
     *            expire，单位：秒
     */
    void expire(String key, long expire);

    void expire(final String key, long expire, TimeUnit timeUnit);
}
