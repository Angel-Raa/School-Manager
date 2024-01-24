package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.service.intefaces.StudentService;
import com.github.angel.raa.modules.utils.DTO.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }

}
