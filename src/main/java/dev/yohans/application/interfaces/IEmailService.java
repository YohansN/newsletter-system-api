package dev.yohans.application.interfaces;

import dev.yohans.core.models.Email;
import dev.yohans.core.models.Post;

public interface IEmailService {
    Email emailSetUp(Post emailContent);
    void sendEmail(Email email);
}
