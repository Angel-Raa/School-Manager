package com.github.angel.raa.modules.utils.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CourseDTO(Long courseId,
                        @NotEmpty(message = "Campo curso no puede esta vacio")
                        String name,
                        @Size(min = 10, max = 200, message = "La descripcion debe tener entre 10 y 200 caracteres")
                        String description
) {
}
