package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired private BoardService service;

    @GetMapping("/list")
    public String list(@RequestParam(value="rs") String rs, Model model) {

        BoardDomain param = new BoardDomain();
        List<BoardDomain> list = null;
        System.out.println("rs : " + rs);

        if (rs.equals("0") || rs.equals("1") || rs.equals("2") || rs.equals("3") || rs.equals("4") || rs.equals("5") || rs.equals("6") || rs.equals("7") ) {
            System.out.println("rsad 작동");
            System.out.println(rs);
//            if (rs.equals("0")) { param.setRsad("중구"); }
//            else if (rs.equals("1")) { param.setRsad("수성구"); }
//            else if (rs.equals("2")) { param.setRsad("동구"); }
//            else if (rs.equals("3")) { param.setRsad("서구"); }
//            else if (rs.equals("4")) { param.setRsad("남구"); }
//            else if (rs.equals("5")) { param.setRsad("북구"); }
//            else if (rs.equals("6")) { param.setRsad("달서구"); }
//            else if (rs.equals("7")) { param.setRsad("달성군"); }
            System.out.println(param);
            switch (rs) {
                case "0": param.setRsad("중구"); break;
                case "1": param.setRsad("수성구"); break;
                case "2": param.setRsad("동구");  break;
                case "3": param.setRsad("서구"); break;
                case "4": param.setRsad("남구"); break;
                case "5": param.setRsad("북구"); break;
                case "6": param.setRsad("달서구"); break;
                case "7": param.setRsad("달성군"); break;
            }
            list = service.selBoardRsad(param);
        } else if (rs.equals("8") || rs.equals("9") || rs.equals("10") || rs.equals("11") || rs.equals("12") || rs.equals("13") ) {
            System.out.println("rsc 작동");
            System.out.println(rs);
            switch (rs) {
                case "8": param.setRsc("한식"); break;
                case "9": param.setRsc("경양식"); break;
                case "10": param.setRsc("일식");  break;
                case "11": param.setRsc("중국식"); break;
                case "12": param.setRsc("기타"); break;
                case "13": param.setRsc("분식"); break;
            }
            list = service.selBoardRsc(param);
        } else {
            System.out.println("ㅠ");
        }
        // System.out.println("list : " + list.toString());
        // System.out.println("list[0] : " + list.get(0));
        model.addAttribute("list", list);

        return "board/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value="iboard") int iboard, Model model, RsvEntity rsvEntity){
        model.addAttribute("boardEntity", service.selBoard(iboard));
        return "board/detail";
    }

  //  @GetMapping("/cmt")
  //  public CmtEntity insCmt(CmtEntity param){
  //      return service.insCmt();//    }

    @GetMapping("/cmt")
    public List<CmtDomain> cmtList(CmtEntity param){
        return service.selCmtList();
    }

    @GetMapping("/rsv")
    public String rsvList(@RequestParam(value = "iuser") int iuser, Model model){
        model.addAttribute("rsvEntity", service.selRsvList(iuser));
        return "user/mypage";
    }

    @PostMapping("/rsv")
    public String rsv(@RequestParam(value = "rsvEntity") RsvEntity rsvEntity, Model model){
        model.addAttribute("rsvEntity", service.insRsv(rsvEntity));
        return "board/detail";
    }
}
