package dev.yohans.core.exceptions.subscriber;

public class FailedToFindAllSubscribers extends RuntimeException {
    public FailedToFindAllSubscribers() { super("Houve uma falha ao buscar por assinantes."); }
    public FailedToFindAllSubscribers(String message) { super(message); }
}
