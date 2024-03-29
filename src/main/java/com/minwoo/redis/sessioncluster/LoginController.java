package com.minwoo.redis.sessioncluster;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    /* Redis내의 session 저장 형태 예시
        {
            "sessionId1": {
              "attribute1": "value1",
              "attribute2": "value2",
              ...
            },
            "sessionId2": {
              "attribute1": "value1",
              "attribute2": "value2",
              ...
            },
            ...
        }
     */

    @GetMapping("/login")
    public String login(@RequestParam String name,
                        HttpSession httpSession) {
        // redis에 {sessionId : {name : value}} 형태로 저장됨 -
        // redis/session은 application.yml 설정(부트 3.0은 자동 설정)
        httpSession.setAttribute("name", name);

        return "login success";
    }

    @GetMapping("/name")
    public String getName(HttpSession httpSession) {
        // redis에 저장된 attribute value들에서 꺼냄
        // "sessionId1": {
        //              "attribute1": "value1",
        //              "attribute2": "value2",
        //              ...
        //            }
        String name = (String) httpSession.getAttribute("name");

        return name;
    }

}