package dev.yohans.infra.controllers;

import dev.yohans.application.interfaces.ISubscriberService;
import dev.yohans.core.models.Subscriber;
import dev.yohans.core.models.dtos.CancelSubscription;
import dev.yohans.core.models.dtos.UserRegistration;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subscriber")
public class SubscriberController {
    private final ISubscriberService subscriberService;

    public SubscriberController(ISubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> userRegister(@RequestBody @Valid UserRegistration dto){
        subscriberService.userRegister(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Assinante registrado com sucesso.");
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelSubscription(@RequestBody @Valid CancelSubscription dto){
        subscriberService.cancelSubscription(dto.email());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Subscriber>> getAllSubscribers(@PageableDefault(size = 10, page = 0) Pageable pageable){
        var response = subscriberService.getAllSubscribers(pageable);
        if(response == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
