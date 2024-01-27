package com.github.angel.raa.modules.utils.DTO;

import jakarta.validation.constraints.NotEmpty;

public record CourseDTO(Long courseId,
                        @NotEmpty(message = "Campo curso no puede esta vacio")
                        String name,
                        String description
) {
}
