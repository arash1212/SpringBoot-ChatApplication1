package com.example.springbootchatapplication1.utils;

import com.example.springbootchatapplication1.model.entity.redis.OtpRedisHash;
import com.example.springbootchatapplication1.model.repository.redis.OtpRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRedisRepository otpRedisRepository;

    public String generateOtp() {
        Random random = new Random();
        String otp = String.format("%04d", random.nextInt(10000));
        while (this.otpRedisRepository.valueGet(otp) != null) {
            otp = String.format("%04d", random.nextInt(10000));
        }
        return otp;
    }

    public String create(Long userId) {
        OtpRedisHash otpRedisHash = new OtpRedisHash();
        otpRedisHash.setOtp(this.generateOtp());
        otpRedisHash.setUserId(userId);
        this.otpRedisRepository.valueSet(otpRedisHash.getOtp(), otpRedisHash);
        return otpRedisHash.getOtp();
    }

    public OtpRedisHash get(String otp) {
        return this.otpRedisRepository.valueGet(otp);
    }

    public String get(Long UserId) {
        return null;
    }

    public void delete(Long id) {
        this.otpRedisRepository.valueDelete(id);
    }
}
