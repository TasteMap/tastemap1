package com.daedong.tastemap.security;

import com.daedong.tastemap.security.model.CustomUserPrincipal;
import com.daedong.tastemap.user.model.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class AuthenticationFacadeImpl implements IAuthenticationFacade {
    @Override
    public UserEntity getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal userDetails= (CustomUserPrincipal) auth.getPrincipal();

        return userDetails.getUser();
    }

    @Override
    public int getLoginUserPk(){
        return getLoginUser().getIuser();
    }
}

