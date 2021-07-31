package com.daedong.tastemap.board.model;

import com.daedong.tastemap.board.model.RsvEntity;
import lombok.Getter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Getter
@EnableAutoConfiguration
public class RsvDTO extends RsvEntity {
    private String rsnm;
}
