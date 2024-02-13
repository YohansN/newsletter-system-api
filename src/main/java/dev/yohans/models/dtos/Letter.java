package dev.yohans.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Letter(@NotBlank(message = "{titulo.obrigatorio}") @Size(max = 256, message = "{titulo.tamanhoinvalido}") String title,
                     @Size(max = 256, message = "{sinopse.tamanhoinvalido}") String synopsis,
                     @NotBlank(message = "{autor.obrigatorio}") @Size(max = 256, message = "{autor.tamanhoinvalido}") String author,
                     @NotBlank(message = "{conteudo.obrigatorio}") String content) { }
