package com.daedong.tastemap.common;

public interface EmailService {
    void sendMimeMessage(String to, String subject, String text);
}

