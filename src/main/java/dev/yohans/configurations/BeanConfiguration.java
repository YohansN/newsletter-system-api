package dev.yohans.configurations;

import dev.yohans.application.interfaces.IEmailService;
import dev.yohans.application.interfaces.ISubscriberService;
import dev.yohans.infra.gateways.PostRepositoryGateway;
import dev.yohans.infra.gateways.SubscriberRepositoryGateway;
import dev.yohans.infra.persistence.PostRepository;
import dev.yohans.infra.persistence.SubscriberRepository;
import dev.yohans.application.services.EmailService;
import dev.yohans.application.services.PostService;
import dev.yohans.application.services.SubscriberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class BeanConfiguration {
    @Bean
    PostService postService(PostRepositoryGateway postRepositoryGateway, IEmailService emailService){
        return new PostService(postRepositoryGateway, emailService);
    }
    @Bean
    PostRepositoryGateway postRepositoryGateway(PostRepository postRepository){
        return new PostRepositoryGateway(postRepository);
    }

    @Bean
    SubscriberService subscriberService(SubscriberRepositoryGateway subscriberRepositoryGateway){
        return new SubscriberService(subscriberRepositoryGateway);
    }
    @Bean
    SubscriberRepositoryGateway subscriberRepositoryGateway(SubscriberRepository subscriberRepository){
        return new SubscriberRepositoryGateway(subscriberRepository);
    }

    @Bean
    EmailService emailService(JavaMailSender javaMailSender, ISubscriberService subscriberService){
        return new EmailService(javaMailSender, subscriberService);
    }
}
