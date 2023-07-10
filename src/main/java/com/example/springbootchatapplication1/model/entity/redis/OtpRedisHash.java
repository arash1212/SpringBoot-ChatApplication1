package com.example.springbootchatapplication1.model.entity.redis;

import com.example.springbootchatapplication1.model.entity.interfaces.IRedisHash;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@RedisHash(value = "OTP", timeToLive = 120)
public class OtpRedisHash implements IRedisHash, Serializable {
    @Id
    private Long id;
    private String otp;
    private Long userId;
}
