package dev.yohans.controllers;

import dev.yohans.models.Subscriber;
import dev.yohans.models.dtos.CancelSubscription;
import dev.yohans.models.dtos.UserRegistration;
import dev.yohans.repositories.SubscriberRepository;
import dev.yohans.services.SubscriberService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {
    @Autowired
    SubscriberService subscriberService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> userRegister(@RequestBody @Valid UserRegistration dto){
        if(subscriberService.userRegister(dto))
            return ResponseEntity.status(HttpStatus.OK).build();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelSubscription(@RequestBody @Valid CancelSubscription dto){
        if(subscriberService.cancelSubscription(dto.email()))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping
    public ResponseEntity<List<Subscriber>> getAllSubscribers(@PageableDefault(size = 10, page = 0) Pageable pageable){
        var response = subscriberService.getAllSubscribers(pageable);
        if(response != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
