package dev.yohans.services;

import dev.yohans.models.Subscriber;
import dev.yohans.models.dtos.UserRegistration;
import dev.yohans.repositories.SubscriberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepository;

    @Transactional
    public boolean userRegister(UserRegistration dto){
        var subscriber = new Subscriber(dto);
        subscriberRepository.save(subscriber);
        return true;
    }

    public List<String> getAllEmailsFromActiveSubscribers(){
        List<String> emailList = subscriberRepository.findAllByIsActiveTrue();
        return emailList;
    }
}
