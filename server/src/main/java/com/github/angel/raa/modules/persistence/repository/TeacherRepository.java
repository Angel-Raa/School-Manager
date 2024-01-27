package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
