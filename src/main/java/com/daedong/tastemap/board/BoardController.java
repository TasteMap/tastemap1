package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired private BoardService service;

    @GetMapping("/list")
    public String list(@RequestParam(value="rs", defaultValue="0") String rs, Model model) {

        BoardDomain param = new BoardDomain();

        switch (rs) {
            case "0": case "1": case "2": case "3": case "4": case "5": case "6": case "7":
                param.setRsad(rs);
                model.addAttribute("list", service.selBoardRsad(param));
                break;

            case "8": case "9": case "10": case "11": case "12": case "13":
                param.setRsad(rs);
                model.addAttribute("list",service.selBoardRsc(param));
        }
        return "/board/list";
    }


    @GetMapping("/detail")
    public BoardEntity detail(BoardEntity boardEntity){
        BoardDomain param = new BoardDomain();

        return service.selBoard();}

//    @ResponseBody
//    @GetMapping("/cmt")
//    public CmtEntity insCmt(CmtEntity param){
//        return service.insCmt();
//    }

    @ResponseBody
    @GetMapping("/cmt")
    public List<CmtDomain> cmtList(CmtEntity param){
        return service.selCmtList();
    }
}
