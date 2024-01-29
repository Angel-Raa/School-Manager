package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.DTO.SubjectsDTO;
import com.github.angel.raa.modules.utils.api.Response;

import java.util.List;

public interface SubjectsService {
    List<SubjectsDTO > getAllSubjects(); //<-- List<SubjectsDTO>
    SubjectsDTO getSubjectById(Long subjectsId); //<-- SubjectsDTO
    Response createSubject(SubjectsDTO subject); //<-- SubjectsDTO
    Response updateSubject(Long subjectsId, SubjectsDTO subject); //<-- SubjectsDTO
    Response deleteSubject(Long id); //<-- void
}
