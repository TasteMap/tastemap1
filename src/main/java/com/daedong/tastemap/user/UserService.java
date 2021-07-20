package com.daedong.tastemap.user;

import com.daedong.tastemap.common.EmailService;
import com.daedong.tastemap.common.MySecurityUtils;
import com.daedong.tastemap.security.IAuthenticationFacade;
import com.daedong.tastemap.security.model.UserDetailsServiceImpl;
import com.daedong.tastemap.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService{
    @Autowired
    private EmailService email;

    @Autowired
    private MySecurityUtils secUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserDetailsServiceImpl userDetailService;

    public int join(UserEntity param){
        String authCd = secUtils.getRandomDigit(5);
        //비밀번호 암호화
        String hashedPw = passwordEncoder.encode(param.getPw());
        param.setPw(hashedPw);
        param.setAuthCd(authCd);
        param.setProvider("local");
        int result = userDetailService.join(param);

        if(result == 1){ //메일쏘기(id, authcd값을 메일로 쏜다)
            String subject = "인증메일입니다";
            String txt = String.format("<a href=\"http://localhost:8090/user/auth?email=%s&authCd=%s\">인증하기</a>"
                    , param.getEmail(), authCd);
            email.sendMimeMessage(param.getEmail(), subject, txt);
        }
        return result;
    }

    //이메일 인증처리
    public int auth(UserEntity param){
        return mapper.auth(param);
    }

    public int idChk(UserEntity param){
        UserEntity param2 = mapper.idChk(param);
        if(param2 == null){
            return 0;
        }
        return 1;

    }
}

