package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.BoardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardList();
    List<BoardDomain> selBoardList_Fav();
    List<BoardDomain> selBoardList_rsad();
    List<BoardDomain> selBoardList_rsc();
    BoardEntity selBoard();
}
