package com.yitian.sq.security;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailRunnable implements Runnable {
    private SimpleMailMessage mailMessage;
    private static JavaMailSenderImpl mailSender;

    public static void setMailSender(JavaMailSenderImpl mailSender) {
        EmailRunnable.mailSender = mailSender;
    }
    //构造方法
    public EmailRunnable(SimpleMailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    @Override
    public void run() {
        mailSender.send(mailMessage);
    }

}
