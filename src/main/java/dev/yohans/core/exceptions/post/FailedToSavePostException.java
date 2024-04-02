package dev.yohans.core.exceptions.post;

public class FailedToSavePostException extends RuntimeException {
    public FailedToSavePostException() { super("Houve uma falha ao salvar a publicação."); }
    public FailedToSavePostException(String message) { super(message); }
}
