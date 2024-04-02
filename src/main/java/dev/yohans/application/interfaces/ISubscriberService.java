package dev.yohans.application.interfaces;

import dev.yohans.core.models.Subscriber;
import dev.yohans.core.models.dtos.UserRegistration;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ISubscriberService {
    boolean userRegister(@Valid UserRegistration dto);
    List<Subscriber> getAllSubscribers(Pageable pageable);
    boolean cancelSubscription(String email);
    List<String> getAllEmailsFromActiveSubscribers();
}
