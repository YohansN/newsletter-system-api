package dev.yohans.core.exceptions.subscriber;

public class FailedToFindActiveSubscribersException extends RuntimeException {
    public FailedToFindActiveSubscribersException() { super("Houve uma falha ao buscar assinantes ativos."); }
    public FailedToFindActiveSubscribersException(String message) { super(message); }
}
