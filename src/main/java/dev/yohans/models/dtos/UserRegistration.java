package dev.yohans.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistration(@NotBlank(message = "{nome.obrigatorio}") @Size(max = 256, min = 2, message = "{nome.tamanhoinvalido}") String name,
                               @NotBlank(message = "{email.obrigatorio}") @Email(message = "{email.invalido}") @Size(max = 256, message = "{email.tamanhoinvalido}") String email) { }
