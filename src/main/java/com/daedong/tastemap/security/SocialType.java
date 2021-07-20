package com.daedong.tastemap.security;

public enum SocialType {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    KAKAO("kakao");

    private String name;
    private final String ROLE_PREFIX = "ROLE_";

    SocialType(String name) {

        this.name = name;
    }

    public String getValue() {

        return name;
    }
    public String getRoleType() {

        return ROLE_PREFIX + name.toUpperCase();
    }
    public boolean isEquals(String authority) {

        return this.getRoleType().equals(authority);
    }
}