package com.zcl.desginer.redis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


/**
 * @ClassName RedisssonService
 * @Description Redis 缓存服务
 * @Author seanli
 * @Date 2019-04-23 15:12
 * @Version 1.0
 **/
@Service("redissonService")
@Slf4j
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    public void getRedissonClient() throws IOException {
        Config config = redissonClient.getConfig();
        log.info(config.toJSON().toString());
    }

    /**
     * 获取字符串对象
     */
    public <T> RBucket<T> getRBucket(String objectName) {
        RBucket<T> bucket = redissonClient.getBucket(objectName);
        return bucket;
    }

    /**
     * 获取字符串对象
     */
    public RBuckets getRBuckets() {
        return redissonClient.getBuckets();
    }

    public <V> Map<String, V> getMapByRBuckets(String[] keys) {
        if (null == keys || keys.length == 0) {
            return new HashMap<>();
        }
        RBuckets rbuckets = redissonClient.getBuckets();
        return rbuckets.get(keys);
    }

    /**
     * 获取Map对象
     */
    public <K, V> RMap<K, V> getRMap(String objectName) {
        RMap<K, V> map = redissonClient.getMap(objectName);
        return map;
    }

    /**
     * 获取RMapCache对象
     */
    public <K, V> RMapCache<K, V> getRMapCache(String objectName) {
        RMapCache<K, V> map = redissonClient.getMapCache(objectName);
        return map;
    }

    /**
     * 获取有序集合
     */
    public <V> RSortedSet<V> getRSortedSet(String objectName) {
        RSortedSet<V> sortedSet = redissonClient.getSortedSet(objectName);
        return sortedSet;
    }

    public <V> RScoredSortedSet<V> getScoredSortedSet(String objectName) {
        RScoredSortedSet<V> scoredSortedSet = redissonClient.getScoredSortedSet(objectName);
        return scoredSortedSet;
    }


    /**
     * 获取集合
     */
    public <V> RSet<V> getRSet(String objectName) {
        RSet<V> rset = redissonClient.getSet(objectName);
        return rset;
    }

    /**
     * 获取列表
     */
    public <V> RList<V> getRList(String objectName) {
        RList<V> rlist = redissonClient.getList(objectName);
        return rlist;
    }

    /**
     * 获取队列
     */
    public <V> RQueue<V> getRQueue(String objectName) {
        RQueue<V> rqueue = redissonClient.getQueue(objectName);
        return rqueue;
    }

    /**
     * 获取双端队列
     */
    public <V> RDeque<V> getRDeque(String objectName) {
        RDeque<V> rdeque = redissonClient.getDeque(objectName);
        return rdeque;
    }

    /**
     * 获取Multimap
     */
    public <K, V> RListMultimap<K, V> getRListMultimap(String objectName) {
        RListMultimap<K, V> rlistMultimap = redissonClient.getListMultimap(objectName);
        return rlistMultimap;
    }

    /**
     * 获取锁
     */
    public RLock getRLock(String objectName) {
        RLock rlock = redissonClient.getLock(objectName);
        return rlock;
    }

    /**
     * 获取读取锁
     */
    public RReadWriteLock getRwLock(String objectName) {
        RReadWriteLock rwlock = redissonClient.getReadWriteLock(objectName);
        return rwlock;
    }

    /**
     * 获取原子数
     */
    public RAtomicLong getRAtomicLong(String objectName) {
        RAtomicLong ratomicLong = redissonClient.getAtomicLong(objectName);
        return ratomicLong;
    }

    /**
     * 获取记数锁
     */
    public RCountDownLatch getRCountDownLatch(String objectName) {
        RCountDownLatch rcountDownLatch = redissonClient.getCountDownLatch(objectName);
        return rcountDownLatch;
    }

    /**
     * 获取记数锁
     *
     * @return success
     */
    public boolean setSemaphore(String objectName, int numberOfPermits) {
        RSemaphore semaphore = redissonClient.getSemaphore(objectName);
        semaphore.trySetPermits(numberOfPermits);//设置许可
        return true;
    }

    public RPermitExpirableSemaphore getSemaphore(String objectName) {
        RPermitExpirableSemaphore semaphore = redissonClient
            .getPermitExpirableSemaphore(objectName);
        return semaphore;
    }


}
