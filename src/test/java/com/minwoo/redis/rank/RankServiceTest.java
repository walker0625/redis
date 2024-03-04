package com.minwoo.redis.rank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RankServiceTest {

    @Autowired RankService rankService;

    @Test
    void getRanks() {

        StopWatch stopWatch = new StopWatch();

        // getRank
        stopWatch.start();
        Long userId100Rank = rankService.getUserRank("userId_10000");
        stopWatch.stop();

        System.out.println("getRank : " + stopWatch.getTotalTimeMillis()); // 18

        // get3
        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        List<String> topThreeRanks = rankService.getTopThreeRanks();
        stopWatch2.stop();

        System.out.println("getThreeRank : " + stopWatch2.getTotalTimeMillis()); // 5
    }

    @Test
    void inMemorySort() {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            int score = (int) (Math.random() * 1000000);
            integers.add(score);
        }

        Collections.sort(integers);
        stopWatch.stop();

        System.out.println("inMemorySort : " + stopWatch.getTotalTimeMillis()); // 400
    }

    //@Test
    void inRedisSort() {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        for (int i = 0; i < 1000000; i++) {
            int score = (int) (Math.random() * 1000000);
            rankService.setUserScore("userId_" + i, score);
        }

        stopWatch.stop();

        System.out.println("inRedisSort : " + stopWatch.getTotalTimeMillis());
    }

}