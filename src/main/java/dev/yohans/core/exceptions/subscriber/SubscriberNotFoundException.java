package dev.yohans.core.exceptions.subscriber;

public class SubscriberNotFoundException extends RuntimeException {
    public SubscriberNotFoundException() { super("Esse assinante não foi encontrado."); }
    public SubscriberNotFoundException(String message) { super(message); }
}
