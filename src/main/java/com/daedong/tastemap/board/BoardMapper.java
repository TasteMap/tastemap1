package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.BoardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardRsad(BoardEntity param);
    List<BoardDomain> selBoardRsc(BoardEntity param);
    BoardEntity selBoard();
}
