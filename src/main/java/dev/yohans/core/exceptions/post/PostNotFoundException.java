package dev.yohans.core.exceptions.post;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException() { super("Essa publicação não foi encontrada."); }
    public PostNotFoundException(String message) { super(message); }
}
