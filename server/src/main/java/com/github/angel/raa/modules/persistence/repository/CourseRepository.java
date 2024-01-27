package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
