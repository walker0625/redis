package com.minwoo.redis;

import com.minwoo.redis.pubsub.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisApplication implements CommandLineRunner {

    @Autowired
    ChatService chatService;

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("chat app start");
        chatService.enterChatRoom("testRoom");
    }

}
