package dev.yohans.repositories;

import dev.yohans.models.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> { }
