package com.github.angel.raa.modules.utils.DTO;

import java.util.Set;

public record StudentCourseDTO(String name, Set<CourseDTO> courses) {
}
