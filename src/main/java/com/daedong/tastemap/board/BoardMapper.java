package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.*;
import com.daedong.tastemap.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardRsad(BoardDomain param);
    List<BoardDomain> selBoardRsc(BoardDomain param);
    BoardDomain selBoard(int iboard);
    //코멘트
    int insCmt(CmtEntity param);
    List<CmtDomain> selCmtList(CmtEntity param);
    int delCmt(CmtEntity param);
    int updCmt(CmtEntity param);
    //예약
    int insRsv(RsvEntity param);
    int delRsv(RsvEntity param);
    //    List<RsvDTO> selRsvList(UserEntity param);
    //좋아요
    int insFav(FavEntity param);
    BoardDomain selFav(BoardDomain param);
    int delFav(FavEntity param);
    int getFav(FavEntity param);

}
