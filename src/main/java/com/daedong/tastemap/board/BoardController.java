package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.BoardDomain;
import com.daedong.tastemap.board.model.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired private BoardService service;

    @GetMapping("/list")
    public String test(@RequestParam(value="rsad", defaultValue="0") String rsad, @RequestParam(value="rsc", defaultValue="0") String rsc
        , Model model) {
        BoardEntity param = new BoardEntity();
        param.setRsad(rsad);
        model.addAttribute("list", service.selBoardRsad(param));
        return "/board/list";
    }


    @GetMapping("/detail")
    public BoardEntity detail(BoardEntity boardEntity){return service.selBoard();}




}
