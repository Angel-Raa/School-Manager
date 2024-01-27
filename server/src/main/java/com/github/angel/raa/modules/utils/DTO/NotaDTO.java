package com.github.angel.raa.modules.utils.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record NotaDTO(Long notaId, Long studentId, Long course,
                      @NotEmpty
                      @Min(1)
                      @Max(100)
                      int qualification) {
}
