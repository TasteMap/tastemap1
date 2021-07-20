package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardList();
}
