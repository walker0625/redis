package com.minwoo.redis.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

// 지연이 발생하는 api로 가정
@Service
public class ExternalUserApi {

    public String getUsername(String userId) {
        System.out.println("call getUsername");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (userId.equals("a")) {
            return "adam";
        }

        return null;
    }

    @Cacheable(cacheNames = "userAgeCache", key = "#userId")
    public Integer getAge(String userId) {
        System.out.println("call getAge");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (userId.equals("a")) {
            return 30;
        }

        return null;
    }

}