package com.daedong.tastemap.user;

import com.daedong.tastemap.user.model.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public void login(UserEntity userEntity){}

    @PostMapping("/join")
    public String joinProc(UserEntity userEntity){
        service.join(userEntity);
        return "redirect:login?needEmail=1";
    }

    @GetMapping("/auth")
    public String auth(UserEntity param){
        int result = service.auth(param);
        return "redirect:login?auth=" + result;
    }

    @ResponseBody
    @GetMapping("/idChk")
    public Map<String, Object> idChk(UserEntity param){
        System.out.println(param);
        Map<String, Object> data =  new HashMap<>();
        data.put("result", service.idChk(param));
        return data;
    }

}
