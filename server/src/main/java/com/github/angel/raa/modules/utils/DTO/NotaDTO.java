package com.github.angel.raa.modules.utils.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record NotaDTO(Long notaId, Long studentId, Long courseId,
                      @NotNull(message = "Campo nota no puede se nula") @Min(1) @Max(100)
                      Long qualification) {
}
