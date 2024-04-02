package dev.yohans.infra.gateways;

import dev.yohans.application.gateways.SubscriberGateway;
import dev.yohans.core.models.Subscriber;
import dev.yohans.infra.persistence.SubscriberRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public class SubscriberRepositoryGateway implements SubscriberGateway {
    private final SubscriberRepository subscriberRepository;

    public SubscriberRepositoryGateway(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Transactional
    @Override
    public void saveSubscriber(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    public List<Subscriber> findAllSubscribers(Pageable pageable) {
        return subscriberRepository.findAll(pageable).stream().toList();
    }

    @Override
    public Optional<Subscriber> findSubscriberByEmail(String email) {
        return subscriberRepository.findSubscriberByEmail(email);
    }

    @Override
    public List<String> findAllSubscribersByIsActiveTrue() {
        return subscriberRepository.findAllByIsActiveTrue();
    }
}
