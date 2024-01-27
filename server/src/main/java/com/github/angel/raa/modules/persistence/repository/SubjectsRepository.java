package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.models.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {
}
