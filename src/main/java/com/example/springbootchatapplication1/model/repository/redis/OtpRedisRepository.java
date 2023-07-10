package com.example.springbootchatapplication1.model.repository.redis;

import com.example.springbootchatapplication1.model.entity.redis.OtpRedisHash;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

@Repository
public class OtpRedisRepository extends GenericRedisRepository<OtpRedisHash> {

    @Override
    protected Class<OtpRedisHash> getHashClass() {
        return OtpRedisHash.class;
    }
}
