package dev.yohans.application.gateways;

import dev.yohans.core.models.Subscriber;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface SubscriberGateway {
    void saveSubscriber(Subscriber subscriber);
    List<Subscriber> findAllSubscribers(Pageable pageable);

    Optional<Subscriber> findSubscriberByEmail(String email);
    List<String> findAllSubscribersByIsActiveTrue();

}
