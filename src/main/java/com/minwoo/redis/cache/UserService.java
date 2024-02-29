package com.minwoo.redis.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalUserApi externalUserApi;
    private final StringRedisTemplate usernameRedis;

    public UserDto getProfile(String userId) {

        // 1. redis 직접 사용
        // cache aside 패턴(redis에서 먼저 cache hit 시도)
        String username = null;

        username = usernameRedis.opsForValue().get(userId);

        // 0.5초 delay
        if(username == null) {
            username = externalUserApi.getUsername(userId);
            usernameRedis.opsForValue().set(userId, username, 5, TimeUnit.SECONDS);
        }

        // 2. spring cache 사용(권장됨)
        Integer age = externalUserApi.getAge(userId);

        return new UserDto(username, age);
    }

}