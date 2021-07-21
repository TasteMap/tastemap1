package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired private BoardMapper mapper;

    public List<BoardDomain> selBoardRsad(BoardEntity param) { return mapper.selBoardRsad(param); }
    public List<BoardDomain> selBoardRsc(BoardEntity param) {return mapper.selBoardRsc(param);}
    public BoardEntity selBoard() {return mapper.selBoard();}
}
