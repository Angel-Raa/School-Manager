package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByStudentId(Long studentId); // (studentId = student.id)
    List<Assignment> findByCourseId(Long courseId);  // (courseId = course.id)
    //List<Subjects> findByTeacherId(Long teacherId);  (teacher = teacher.id)

}
