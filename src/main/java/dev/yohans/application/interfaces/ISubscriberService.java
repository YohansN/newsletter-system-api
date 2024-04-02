package dev.yohans.application.interfaces;

import dev.yohans.core.models.Subscriber;
import dev.yohans.core.models.dtos.UserRegistration;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ISubscriberService {
    void userRegister(@Valid UserRegistration dto);
    List<Subscriber> getAllSubscribers(Pageable pageable);
    void cancelSubscription(String email);
    List<String> getAllEmailsFromActiveSubscribers();
}
