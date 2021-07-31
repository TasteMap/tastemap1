package com.daedong.tastemap.user;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.RsvDTO;
import com.daedong.tastemap.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int join(UserEntity param);
    int auth(UserEntity param);
    UserEntity selUser(UserEntity param);
    UserEntity idChk(UserEntity param);
    int updUser(UserEntity param);
    List<RsvDTO> selRsvList(UserEntity param);
    List<BoardDomain> selFavList(UserEntity param);
}

