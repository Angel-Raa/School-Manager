package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.DTO.CourseDTO;
import com.github.angel.raa.modules.utils.DTO.CourseTeacherDTO;
import com.github.angel.raa.modules.utils.DTO.SubjectsDTO;
import com.github.angel.raa.modules.utils.api.Response;

import java.util.List;

public interface CourseService {
    List<CourseDTO > findAll();
    CourseDTO findById(Long id);
    Response create(Long teacherId, CourseDTO courseDTO);
    Response delete(Long courseId);
    Response update(Long courseId, CourseDTO courseDTO);
    List<CourseTeacherDTO> getAllCourseTeachers(); //<-- returns a list of CourseTeacherDTO, not a set<CourseTeacherDTO>
    List <SubjectsDTO> getCourseTeachers(Long teacherId); //<-- returns a set of CourseTeacherDTO, not a list<CourseTeacherDTO>
}
