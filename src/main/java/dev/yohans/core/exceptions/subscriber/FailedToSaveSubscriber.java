package dev.yohans.core.exceptions.subscriber;

public class FailedToSaveSubscriber extends RuntimeException {
    public FailedToSaveSubscriber() { super("Houve uma falha ao salvar assinatura de usuário."); }
    public FailedToSaveSubscriber(String message) { super(message); }
}
