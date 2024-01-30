package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.service.intefaces.StudentService;
import com.github.angel.raa.modules.utils.DTO.StudentCourseDTO;
import com.github.angel.raa.modules.utils.DTO.StudentDTO;
import com.github.angel.raa.modules.utils.api.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
    private final StudentService service;

    @GetMapping("/all-student")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/student-by/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@Valid @PathVariable(value = "id") @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @GetMapping("/all-student-course")
    public ResponseEntity<List<StudentCourseDTO>> getAllStudentCourse() {
        return ResponseEntity.ok(service.getStudentCourses());
    }
    @PostMapping("/add-student")
    public ResponseEntity<Response> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(service.addStudent(studentDTO));
    }

    @PostMapping("/subscribe-course/{courseId}/student/{studentId}")
    public ResponseEntity<Response> subscribeCourse(@Valid @PathVariable(value = "courseId") @Min(1) Long courseId, @PathVariable(value = "studentId") @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long studentId) {
        return ResponseEntity.ok(service.subscribeCourse(courseId, studentId));
    }

    @PutMapping("/update-student/{id}")
    public ResponseEntity<Response> updateStudent(@Valid @PathVariable(value = "id") @Min(1) Long id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(service.updateStudent(id, studentDTO));
    }


    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<Response> deleteStudent(@Valid @PathVariable(value = "id") @Min(1) Long id) {
        return ResponseEntity.ok(service.deleteStudent(id));
    }

}
