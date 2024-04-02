package dev.yohans.application.interfaces;

import dev.yohans.core.models.Email;

public interface IEmailService {
    void sendEmail(Email email);
}
