package com.daedong.tastemap.board;

import com.daedong.tastemap.board.model.*;
import com.daedong.tastemap.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardMapper mapper;
    @Autowired
    private IAuthenticationFacade auth;

    public List<BoardDomain> selBoardRsad(BoardDomain param) {
        return mapper.selBoardRsad(param);
    }

    public List<BoardDomain> selBoardRsc(BoardDomain param) {
        return mapper.selBoardRsc(param);
    }

    public BoardDomain selBoard(int iboard) {
        return mapper.selBoard(iboard);
    }

    public int insCmt(CmtEntity param){
        param.setIuser(auth.getLoginUserPk());
        return mapper.insCmt(param);
    }

    public List<CmtDomain> selCmtList(CmtEntity param) {
        return mapper.selCmtList(param);
    }

    public int delCmt(CmtEntity param){
        param.setIuser(auth.getLoginUserPk());
        return mapper.delCmt(param);
    }

    public int updCmt(CmtEntity param){
        param.setIuser(auth.getLoginUserPk());
        return mapper.updCmt(param);
    }

    public int insRsv(RsvEntity param) {
        return mapper.insRsv(param);
    }

    public int delRsv(RsvEntity param) {
        return mapper.delRsv(param);
    }
    //    public List<RsvDTO> selRsvList(UserEntity param) {return mapper.selRsvList(param);}
//    public BoardDomain selFav(int iboard) {
//        return mapper.selFav(iboard);
//    }
//
//    public List<BoardDomain> selFavList(BoardDomain param) {
//        return mapper.selFavList(param);
//    }
//

    public int insFav(FavEntity param) {
        param.setIuser(auth.getLoginUserPk());
        return mapper.insFav(param);
    }

    public BoardDomain selFav(BoardDomain param) {
        return mapper.selFav(param);
    }

    public int delFav(FavEntity param) {
        param.setIuser(auth.getLoginUserPk());
        return mapper.delFav(param);
    }

    public int getFav(FavEntity param){
        return mapper.getFav(param);
    }
}
