package com.daedong.tastemap.user;

import com.daedong.tastemap.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int join(UserEntity param);
    int auth(UserEntity param);
    UserEntity selUser(UserEntity param);
    UserEntity idChk(UserEntity param);
    int updUser(UserEntity param);
}

