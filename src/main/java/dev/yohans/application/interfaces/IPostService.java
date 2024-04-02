package dev.yohans.application.interfaces;

import dev.yohans.core.models.Post;
import dev.yohans.core.models.dtos.Letter;
import dev.yohans.core.models.dtos.PostDetails;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IPostService {
    boolean postingLetter(Letter letter);
    List<PostDetails> getAllPosts(Pageable pageable);
    Post getPostById(Long id);
    Long getTotalNumberOfPosts();
}
