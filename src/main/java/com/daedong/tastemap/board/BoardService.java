package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired private BoardMapper mapper;

    public List<BoardDomain> selBoardRsad(BoardDomain param) { return mapper.selBoardRsad(param); }
    public List<BoardDomain> selBoardRsc(BoardDomain param) {return mapper.selBoardRsc(param);}
    public BoardDomain selBoard(int iboard) {return mapper.selBoard(iboard);}
    public CmtEntity insCmt(){return mapper.insCmt();}
    public List<CmtDomain> selCmtList() {return mapper.selCmtList();}
    public int insRsv(RsvEntity param){return mapper.insRsv(param);}
    public int delRsv(RsvEntity param){return mapper.delRsv(param);}
    public List<RsvEntity> selRsvList(int iuser) {return mapper.selRsvList(iuser);}
}
