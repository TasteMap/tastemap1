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
    CmtEntity insCmt();
    List<CmtDomain> selCmtList();
    int insRsv(RsvEntity param);
    int delRsv(RsvEntity param);
//    List<RsvDTO> selRsvList(UserEntity param);
    int insFav(FavEntity param);
    BoardDomain selFav(BoardDomain param);
    int delFav(FavEntity param);
}
