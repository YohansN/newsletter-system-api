package dev.yohans.controllers;

import dev.yohans.models.Subscriber;
import dev.yohans.models.dtos.UserRegistration;
import dev.yohans.repositories.SubscriberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {
    @Autowired
    SubscriberRepository subscriberRepository;

    @PostMapping
    @Transactional
    public ResponseEntity userRegister(@RequestBody @Valid UserRegistration dto){
        var subscriber = new Subscriber(dto);
        subscriberRepository.save(subscriber);
        return ResponseEntity.ok("Assinatura concluída!");
    }

    @GetMapping
    public List<Subscriber> getAllSubscribers(){
        var res = subscriberRepository.findAll();
        return res;
    }
}
