package dev.yohans.application.gateways;

import dev.yohans.core.models.Post;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface PostGateway {
    void savePost(Post post);
    List<Post> findAllPosts(Pageable pageable);
    Optional<Post> findPostById(Long id);
    Long getTotalNumberOfPosts();
}
