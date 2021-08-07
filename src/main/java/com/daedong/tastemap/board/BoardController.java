package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.*;
import com.daedong.tastemap.security.model.CustomUserPrincipal;
import com.daedong.tastemap.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired private BoardService service;

    @GetMapping("/list")
    public String list(@RequestParam(value="rs") String rs, @AuthenticationPrincipal CustomUserPrincipal userDetail, Model model) {

        BoardDomain param = new BoardDomain();
        param.setIuser(userDetail.getUser().getIuser());

        List<BoardDomain> list = null;
        System.out.println("rs : " + rs);

        if (rs.equals("0") || rs.equals("1") || rs.equals("2") || rs.equals("3") || rs.equals("4") || rs.equals("5") || rs.equals("6") || rs.equals("7") ) {
            System.out.println("rsad 작동");
            System.out.println(rs);
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
    public String detail(@RequestParam(value="iboard") int iboard, Model model, RsvEntity rsvEntity, BoardDomain boardDomain){
        BoardDomain param = new BoardDomain();
        param.setIboard(iboard);
        model.addAttribute("boardDomain", service.selFav(param));
        model.addAttribute("boardEntity", service.selBoard(iboard));
        return "board/detail";
    }
    @ResponseBody
    @RequestMapping(value= "/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtIns(@RequestBody CmtEntity param){
        System.out.println("param = " + param);
        int result = service.insCmt(param);
        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return data;
    }

    @ResponseBody
    @RequestMapping("/cmt/{iboard}")
    public List<CmtDomain> cmtSel(@PathVariable("iboard") int iboard){
        CmtEntity param = new CmtEntity();
        param.setIboard(iboard);
        System.out.println("param=" + param);
        return service.selCmtList(param);
    }


    @ResponseBody
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> cmtDel(CmtEntity param, @PathVariable int icmt){
        param.setIcmt(icmt);
        int result = service.delCmt(param);
        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return data;

    }


    @ResponseBody
    @RequestMapping(value="/cmt", method = RequestMethod.PUT)
    public Map<String, Integer> cmtUpd(@RequestBody CmtEntity param){
        int result = service.updCmt(param);
        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return data;
    }

//    @GetMapping("/rsv")
//    public String rsvList(@RequestParam(value = "iuser") int iuser, Model model){
//        UserEntity param = new UserEntity();
//        param.setIuser(iuser);
//        model.addAttribute("rsvDTO", service.selRsvList(param));
//        return "user/mypage";
//    }

    @PostMapping("/rsv")
    public String rsv(RsvEntity rsvEntity, Model model){
        model.addAttribute("rsvEntity", service.insRsv(rsvEntity));
        return "redirect:/board/detail?iboard=" + rsvEntity.getIboard();
    }

    @ResponseBody
    @PostMapping("/fav")
    public int insFav(@RequestParam(value="iboard") int iboard, FavEntity favEntity) {
        System.out.println(favEntity.getIboard());
        return service.insFav(favEntity);
    }

    @ResponseBody
    @DeleteMapping("/fav")
    public int delFav(@RequestParam(value="iboard") int iboard, FavEntity favEntity) {
        System.out.println(favEntity.getIboard());
        return service.delFav(favEntity);
    }

    @ResponseBody
    @GetMapping("/fav")
    public int getFav(@RequestParam(value="iboard") FavEntity favEntity, @AuthenticationPrincipal CustomUserPrincipal userDetail) {
        System.out.println(favEntity.getIboard());
        favEntity.setIuser(userDetail.getUser().getIuser());
        System.out.println("asdasd"+service.getFav(favEntity));
        return service.getFav(favEntity);
    }
}
