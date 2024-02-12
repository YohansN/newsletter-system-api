package dev.yohans.repositories;

import dev.yohans.models.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
    @Query("SELECT s.email FROM Subscriber s WHERE s.isActive = true")
    List<String> findAllByIsActiveTrue();

    @Query("SELECT s FROM Subscriber s WHERE s.email = :email")
    Optional<Subscriber> findSubscriberByEmail(String email);
}
