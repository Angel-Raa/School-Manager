package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.models.Student;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(@NotNull String email);
    Optional<Student> findByName(@NotNull String name);
}