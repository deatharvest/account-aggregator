package com.jn.account.email.service;


import com.jn.account.email.AccountEmailException;

/**
 * Created by death on 2016/6/10.
 */
public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText)
        throws AccountEmailException;
}
