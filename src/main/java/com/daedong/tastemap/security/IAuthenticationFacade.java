package com.daedong.tastemap.security;

import com.daedong.tastemap.user.model.UserEntity;

public interface IAuthenticationFacade {
    UserEntity getLoginUser();
    int getLoginUserPk();
}

