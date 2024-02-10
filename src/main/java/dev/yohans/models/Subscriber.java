package dev.yohans.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "Subscribers")
@Entity(name = "Subscriber")
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Email
    private String email;
    private Date signupDate;

    Subscriber(String name, String email){
        this.name = name;
        this.email = email;
        this.signupDate = new Date();
    }

}
