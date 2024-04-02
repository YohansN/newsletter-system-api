package dev.yohans.core.exceptions.subscriber;

public class SubscriberAlreadyExistsException extends RuntimeException {
    public SubscriberAlreadyExistsException() { super("Este usuário já é um assinante."); }
    public SubscriberAlreadyExistsException(String message) { super(message); }
}
