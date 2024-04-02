package dev.yohans.infra.gateways;

import dev.yohans.application.gateways.PostGateway;
import dev.yohans.core.models.Post;
import dev.yohans.infra.persistence.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public class PostRepositoryGateway implements PostGateway {
    private final PostRepository postRepository;

    public PostRepositoryGateway(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable).stream().toList();
    }

    @Override
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Long getTotalNumberOfPosts() {
        return postRepository.countBy();
    }
}
