package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.BoardEntity;
import com.daedong.tastemap.board.model.CmtDomain;
import com.daedong.tastemap.board.model.CmtEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardRsad(BoardDomain param);
    List<BoardDomain> selBoardRsc(BoardDomain param);
    BoardEntity selBoard();
    CmtEntity insCmt();
    List<CmtDomain> selCmtList();
}
