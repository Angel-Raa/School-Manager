package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.DTO.StudentCourseDTO;
import com.github.angel.raa.modules.utils.DTO.StudentDTO;
import com.github.angel.raa.modules.utils.api.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(@NotNull Long id);
    Optional<StudentDTO> getStudentByEmail(@NotNull String email);
    Optional<StudentDTO> getStudentByName(@NotNull String name);
    Response addStudent(StudentDTO studentDTO);
    Response updateStudent(Long id, StudentDTO studentDTO);
    Response deleteStudent(Long id);
    List<StudentCourseDTO> getStudentCourses();
    Response subscribeCourse(Long courseId, Long studentId);


}
