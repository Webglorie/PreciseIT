package com.preciseIT.services;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
    void sendEmail(String from, String receiver, String title, String message) throws MessagingException;
}
