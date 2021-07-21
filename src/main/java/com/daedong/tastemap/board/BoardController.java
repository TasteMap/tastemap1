package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired private BoardService service;

    @GetMapping("/list")
    public List<BoardDomain> list() {return service.selBoardList();}

    @GetMapping("/list/star_5")
    public List<BoardDomain> star() {return service.selBoardList_Fav();}

    @GetMapping("/list/jung")
    public List<BoardDomain> jung() {return service.selBoardList_rsad();}

    @GetMapping("/list/korean")
    public List<BoardDomain> korean() {return service.selBoardList_rsc();}

    @GetMapping("/detail")
    public BoardEntity detail(BoardEntity boardEntity){return service.selBoard();}




}
