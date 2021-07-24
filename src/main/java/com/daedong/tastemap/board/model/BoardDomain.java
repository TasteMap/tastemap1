package com.daedong.tastemap.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDomain {
    private int iboard;
    private String rsnm;
    private String rsc;
    private String rsad;
    private int iuser;
    private int favCnt;
    private int isFav;
}
