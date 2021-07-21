package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired private BoardMapper mapper;

    public List<BoardDomain> selBoardList(){return mapper.selBoardList();}
    public List<BoardDomain> selBoardList_Fav(){return mapper.selBoardList_Fav();}
    public List<BoardDomain> selBoardList_rsad(){return mapper.selBoardList_rsad();}
    public List<BoardDomain> selBoardList_rsc(){return mapper.selBoardList_rsc();}
    public BoardEntity selBoard() {return mapper.selBoard();}
}
