package com.prashant.lendingpoint.api.email;

public interface EmailService {
    void sendEmail(Mail mail, String templateName);
}
