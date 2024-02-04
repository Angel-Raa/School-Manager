package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.models.Course;
import com.github.angel.raa.modules.persistence.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByCourse(Course course);

}
