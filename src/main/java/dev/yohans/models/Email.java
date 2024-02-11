package dev.yohans.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Email {
    private String from = "Yohans.DEV";
    private List<String> to;
    private String subject;
    private String body;

    public Email(){ }
    public Email(Post post){
        this.subject = post.getTitle();
        this.body = post.getContent();
    }
}
