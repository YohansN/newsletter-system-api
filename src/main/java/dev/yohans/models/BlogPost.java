package dev.yohans.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{titulo.obrigatorio}") @Size(max = 256, message = "{titulo.tamanhoinvalido}")
    private String title;
    @NotBlank(message = "{conteudo.obrigatorio}")
    private String content;
    private Date publicationDate;
    private boolean IsVisible = true;
}
