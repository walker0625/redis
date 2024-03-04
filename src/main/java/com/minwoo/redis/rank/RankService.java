package com.minwoo.redis.rank;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RankService {

    private static final String LEADERBOARD_KEY = "leaderBoard";

    private final StringRedisTemplate redisTemplate;

    public String setUserScore(String userId, int score) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet(); // sorted Set(정렬) 구조 사용
        zSetOps.add(LEADERBOARD_KEY, userId, score);

        return "success";
    }

    public Long getUserRank(String userId) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();

        return zSetOps.reverseRank(LEADERBOARD_KEY, userId) + 1;
    }

    public List<String> getTopThreeRanks() {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        Set<String> rangeSet = zSetOps.reverseRange(LEADERBOARD_KEY, 0, 2);

        return new ArrayList<>(rangeSet);
    }

}
