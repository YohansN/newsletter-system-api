package dev.yohans.services;

import dev.yohans.models.Subscriber;
import dev.yohans.models.dtos.UserRegistration;
import dev.yohans.repositories.SubscriberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepository;

    @Transactional
    public boolean userRegister(@Valid UserRegistration dto){

        var sub = subscriberRepository.findSubscriberByEmail(dto.email());

        if(sub==null){ //Novo cadastro
            var subscriber = new Subscriber(dto);
            subscriberRepository.save(subscriber);
            return true;
        }

        if(!sub.getIsActive()){ //Email previamente cadastrado porém inativo.
            sub.setIsActive(true);
            return true;
        }

        //Email já cadastrado e ativo.
        return false;
    }

    public List<Subscriber> getAllSubscribers(){
        return subscriberRepository.findAll();
    }

    public List<String> getAllEmailsFromActiveSubscribers(){
        List<String> emailList = subscriberRepository.findAllByIsActiveTrue();
        return emailList;
    }
}
