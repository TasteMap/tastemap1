package com.daedong.tastemap.user.model;

import lombok.*;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private int iuser;
    private String email;
    private String pw;
    private String nm;
    private String tel;
    private String authCd;
    private String regdt;
    private String profileImg;
    private String provider;

}

