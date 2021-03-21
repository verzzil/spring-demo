package ru.itis.springdemo.services.mails;

public interface MailsService {
    void sendMailForConfirm(String mail, String code);
}
