package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired private BoardMapper mapper;

    public List<BoardDomain> selBoardList(){return mapper.selBoardList();}
}
