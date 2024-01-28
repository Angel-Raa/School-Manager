package com.github.angel.raa.modules.utils.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SubjectsDTO(Long subjectsId,
                          @NotNull
                          String name,
                          @Size(min = 10, max = 200, message = "La descripcion debe tener entre 10 y 200 caracteres")
                          String description,
                          Long teacher) {
}
