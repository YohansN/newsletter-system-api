package dev.yohans.infra.persistence;

import dev.yohans.core.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByOrderByPublicationDateDesc(Pageable pageable);

    Long countBy();
}
