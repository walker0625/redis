package com.minwoo.redis.rank;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    @GetMapping("/users/score")
    public String setUserScore(@RequestParam String userId,
                               @RequestParam int score) {
        return rankService.setUserScore(userId, score);
    }

    @GetMapping("/users/{userId}/ranks")
    public Long getUserRank(@PathVariable String userId) {
        return rankService.getUserRank(userId);
    }

    @GetMapping("/users/3ranks")
    public List<String> getTopThreeRanks() {
        return rankService.getTopThreeRanks();
    }

}
