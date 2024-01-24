package com.github.angel.raa.modules.utils.mapper;

import com.github.angel.raa.modules.persistence.models.Student;
import com.github.angel.raa.modules.utils.DTO.StudentDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Component
public class StudentMapper {
    public static StudentDTO mapToDto(@NotNull Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPhone(),
                student.getGender(),
                student.getAddress()
        );
    }
    public static Student mapToEntity(@NotNull StudentDTO studentDTO) {
        return new Student(
                studentDTO.id(),
                studentDTO.name(),
                studentDTO.surname(),
                studentDTO.email(),
                studentDTO.phone() == null ? null : studentDTO.phone().replaceAll(" ", ""),
                studentDTO.gender(),
                studentDTO.address()
        );
    }


}
