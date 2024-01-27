package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.DTO.TeacherDTO;
import com.github.angel.raa.modules.utils.api.Response;

import java.util.List;

public interface TeacherService {
    List<TeacherDTO> findAll();
    TeacherDTO findById(Long id);
    Response save(TeacherDTO teacherDTO);
    Response delete(Long id);
    Response update(Long id, TeacherDTO teacherDTO);
}
