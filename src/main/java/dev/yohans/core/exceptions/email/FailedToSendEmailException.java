package dev.yohans.core.exceptions.email;

public class FailedToSendEmailException extends RuntimeException{
    public FailedToSendEmailException() { super("Houve uma falha ao enviar a publicação por e-mail."); }
    public FailedToSendEmailException(String message) { super(message); }
}
