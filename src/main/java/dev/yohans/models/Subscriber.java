package dev.yohans.models;

import dev.yohans.models.dtos.UserRegistration;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "Subscribers")
@Entity(name = "Subscriber")
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Subscriber {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "{nome.obrigatorio}") @Size(max = 256)
    private String name;
    @Email(message = "{email.invalido}") @NotBlank(message = "{email.obrigatorio}") @Size(max = 256, message = "{email.tamanhoinvalido}")
    private String email;
    private Date signupDate;

    Subscriber(String name, String email){
        this.name = name;
        this.email = email;
        this.signupDate = new Date();
    }

    Subscriber(UserRegistration dto){
        this.name = dto.name();
        this.email = dto.email();
        this.signupDate = new Date();
    }

}
