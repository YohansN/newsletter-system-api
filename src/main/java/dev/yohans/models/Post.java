package dev.yohans.models;

import dev.yohans.models.dtos.Letter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Table(name = "blog_posts")
@Entity(name = "blog_post")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{titulo.obrigatorio}") @Size(max = 256, message = "{titulo.tamanhoinvalido}")
    private String title;
    @NotBlank(message = "{conteudo.obrigatorio}")
    private String synopsis;
    private String author;
    private String content;
    private Date publicationDate;
    private boolean isVisible;

    public Post(Letter dto){
        this.title = dto.title();
        this.synopsis = dto.synopsis();
        this.author = dto.author();
        this.content = dto.content();
        this.publicationDate = new Date();
        this.isVisible = true;
    }
}
