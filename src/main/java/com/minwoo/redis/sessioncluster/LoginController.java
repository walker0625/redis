package com.minwoo.redis.sessioncluster;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam String name,
                        HttpSession httpSession) {
        // application.yml 설정으로 해당 session 정보가 redis에 저장됨
        httpSession.setAttribute("name", name);

        return "login success";
    }

    @GetMapping("/name")
    public String getName(HttpSession httpSession) {
        // application.yml 설정으로 해당 session 정보를 redis에서 조회함
        String name = (String) httpSession.getAttribute("name");

        return name;
    }

}
