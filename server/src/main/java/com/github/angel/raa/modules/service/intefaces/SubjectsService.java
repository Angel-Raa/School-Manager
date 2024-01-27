package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.DTO.SubjectsDTO;

import java.util.List;

public interface SubjectsService {
    List<SubjectsDTO> getAllSubjects(); //<-- List<SubjectsDTO>
    List<SubjectsDTO> getSubjectsByStudent(Long estudentId); //<-- List<SubjectsDTO> (by student id)
    void markAssistance(Long estudentId, Long courseId); //<-- void (by student id and course id)
}
