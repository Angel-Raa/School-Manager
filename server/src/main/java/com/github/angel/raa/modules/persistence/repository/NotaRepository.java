package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByStudentId(Long studentId); // SELECT * FROM notas WHERE student_id = (:studentId)
    List<Nota> findByCourseId(Long courseId); // SELECT * FROM notas WHERE course_id = (:courseId)
}
