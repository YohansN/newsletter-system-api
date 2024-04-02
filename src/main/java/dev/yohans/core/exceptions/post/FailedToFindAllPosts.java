package dev.yohans.core.exceptions.post;

public class FailedToFindAllPosts extends RuntimeException {
    public FailedToFindAllPosts() { super("Houve uma falha ao buscar as publicações."); }
    public FailedToFindAllPosts(String message) { super(message); }
}
