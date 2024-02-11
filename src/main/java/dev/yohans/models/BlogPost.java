package dev.yohans.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Table(name = "blog_posts")
@Entity(name = "blog_post")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Date publicationDate;
    private boolean IsVisible;
}
