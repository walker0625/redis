package com.minwoo.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final StringRedisTemplate stringRedisTemplate;

    @GetMapping("/fruit/{name}")
    public String setFruit(@PathVariable String name) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("fruit", name);

        return "saved " + name;
    }

    @GetMapping("/fruit")
    public String getFruit() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String fruitName = ops.get("fruit");

        return fruitName;
    }

    @GetMapping("/session")
    public String getSession(HttpSession session) {
        session.setAttribute("test", 1);

        return "ok";
    }

}