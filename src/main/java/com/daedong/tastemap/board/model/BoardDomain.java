package com.daedong.tastemap.board.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class BoardDomain extends BoardEntity{
    private int iuser;
    private int favCnt;
    private int isFav;
}
