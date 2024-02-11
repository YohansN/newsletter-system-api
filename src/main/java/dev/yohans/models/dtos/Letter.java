package dev.yohans.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Letter(@NotBlank(message = "{titulo.obrigatorio}") @Size(max = 256, message = "{titulo.tamanhoinvalido}") String title,
                     @NotBlank(message = "{conteudo.obrigatorio}") String content) { }
