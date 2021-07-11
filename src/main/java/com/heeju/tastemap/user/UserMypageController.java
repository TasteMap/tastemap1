package com.heeju.tastemap.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserMypageController {
    @GetMapping("/mypage")
    public String mypage(){
        return "user/mypage";
    }
}
