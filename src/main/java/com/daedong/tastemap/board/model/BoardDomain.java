package com.daedong.tastemap.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDomain extends BoardEntity{
    private int iuser;
    private int favCnt;
    private int isFav;
}
