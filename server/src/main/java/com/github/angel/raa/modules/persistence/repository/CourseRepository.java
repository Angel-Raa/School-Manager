package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.models.Course;
import com.github.angel.raa.modules.persistence.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(Teacher teacher);
}
