package dev.yohans.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDetails {
    private String title;
    private String synopsis;
    private String author;
    private Date publicationDate;
}
