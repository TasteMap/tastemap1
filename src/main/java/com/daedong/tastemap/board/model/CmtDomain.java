package com.daedong.tastemap.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CmtDomain extends CmtEntity{
    private String writer;
    private String profileImg;
}
