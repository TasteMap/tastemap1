package com.daedong.tastemap.main;

import com.daedong.tastemap.user.model.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(UserEntity param){
        return "main/home";
    }

    @GetMapping("/")
    public String home_1(UserEntity param){
        return "main/home";
    }

}

