package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.BoardEntity;
import com.daedong.tastemap.board.model.CmtDomain;
import com.daedong.tastemap.board.model.CmtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired private BoardMapper mapper;

    public List<BoardDomain> selBoardRsad(BoardDomain param) { return mapper.selBoardRsad(param); }
    public List<BoardDomain> selBoardRsc(BoardDomain param) {return mapper.selBoardRsc(param);}
    public BoardEntity selBoard() {return mapper.selBoard();}
    public CmtEntity insCmt(){return mapper.insCmt();}
    public List<CmtDomain> selCmtList() {return mapper.selCmtList();}
}
