package dev.yohans.core.models;

import dev.yohans.core.models.dtos.UserRegistration;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "Subscribers")
@Entity(name = "Subscriber")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Subscriber {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "{nome.obrigatorio}") @Size(max = 256)
    private String name;
    @Email(message = "{email.invalido}") @NotBlank(message = "{email.obrigatorio}") @Size(max = 256, message = "{email.tamanhoinvalido}")
    private String email;
    private Date signupDate;
    private Boolean isActive;

    public Subscriber(@Valid UserRegistration dto){
        this.name = dto.name();
        this.email = dto.email();
        this.signupDate = new Date();
        this.isActive = true;
    }

}
