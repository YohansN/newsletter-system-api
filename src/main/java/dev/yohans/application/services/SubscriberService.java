package dev.yohans.application.services;

import dev.yohans.application.interfaces.ISubscriberService;
import dev.yohans.application.gateways.SubscriberGateway;
import dev.yohans.core.exceptions.subscriber.*;
import dev.yohans.core.models.Subscriber;
import dev.yohans.core.models.dtos.UserRegistration;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriberService implements ISubscriberService {

    private final SubscriberGateway subscriberGateway;

    public SubscriberService(SubscriberGateway subscriberGateway) {
        this.subscriberGateway = subscriberGateway;
    }

    @Transactional
    public void userRegister(@Valid UserRegistration dto){

        Optional<Subscriber> sub = subscriberGateway.findSubscriberByEmail(dto.email());

        if(sub.isEmpty()){ //Novo cadastro
            var subscriber = new Subscriber(dto);
            try {
                subscriberGateway.saveSubscriber(subscriber);
            }catch (Exception ex){
                throw new FailedToSaveSubscriber();
            }
        }

        if(sub.isPresent()){
            var subscriber = sub.get();

            if(!subscriber.getIsActive()){ //Email previamente cadastrado porém inativo.
                subscriber.setName(dto.name());
                subscriber.setIsActive(true);
                subscriber.setSignupDate(new Date());
                try{
                    subscriberGateway.saveSubscriber(subscriber);
                }catch (Exception ex){
                    throw new FailedToSaveSubscriber();
                }
            }
            else throw new SubscriberAlreadyExistsException(); //Email já cadastrado e ativo.
        }
    }

    public List<Subscriber> getAllSubscribers(Pageable pageable){
        try{
            return subscriberGateway.findAllSubscribers(pageable);
        }catch (Exception ex){
            throw new FailedToFindAllSubscribers();
        }
    }

    @Transactional
    public void cancelSubscription(String email){
        Optional<Subscriber> sub = subscriberGateway.findSubscriberByEmail(email);

        if(sub.isEmpty()) throw new SubscriberNotFoundException();

        var subscriber = sub.get();
        subscriber.setIsActive(false);

        try{
            subscriberGateway.saveSubscriber(subscriber);
        }catch (Exception ex){
            throw new FailedToSaveSubscriber();
        }
    }

    public List<String> getAllEmailsFromActiveSubscribers(){
        try {
            return subscriberGateway.findAllSubscribersByIsActiveTrue();
        }catch (Exception ex){
            throw new FailedToFindActiveSubscribersException();
        }

    }
}
