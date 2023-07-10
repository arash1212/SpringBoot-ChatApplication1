package com.example.springbootchatapplication1.model.repository.redis;

import com.example.springbootchatapplication1.model.entity.interfaces.IRedisHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public abstract class GenericRedisRepository<T extends IRedisHash> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    protected abstract Class<T> getHashClass();

    public void valueSet(String key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
        this.redisTemplate.expire(key, getHashClass().getAnnotation(RedisHash.class).timeToLive(), TimeUnit.SECONDS);
    }

    public T valueGet(String id) {
        return (T) this.redisTemplate.opsForValue().get(id);
    }

    public void valueDelete(Long id) {
        String key = getKey(id);
        this.redisTemplate.delete(key);
    }

    public void hashSet(T t) {
        t.setId(this.getId());
        String key = getKey(t.getId());
        this.redisTemplate.opsForHash().put(key, t.getId(), t);
        this.redisTemplate.expire(key, t.getClass().getAnnotation(RedisHash.class).timeToLive(), TimeUnit.SECONDS);
    }

    public T hashGet(Long id) {
        String key = getKey(id);
        return (T) this.redisTemplate.opsForHash().get(key, id);
    }

    private Long getId() {
        return this.redisTemplate.opsForValue().increment(this.getHashName() + ":ID_SEQ");
    }

    private String getKey(Long id) {
        return getHashName() + ":" + id;
    }

    private String getHashName() {
        return this.getHashClass().getAnnotation(RedisHash.class).value();
    }

//    private String getValue(T t) {
//        Field[] fields = t.getClass().getDeclaredFields();
//        String result = "";
//        for (Field field : fields) {
//            field.setAccessible(true);
//            field.ge
//        }
//    }

}
