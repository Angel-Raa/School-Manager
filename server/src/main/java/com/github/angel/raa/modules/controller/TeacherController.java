package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.service.intefaces.TeacherService;
import com.github.angel.raa.modules.utils.DTO.CourseDTO;
import com.github.angel.raa.modules.utils.DTO.TeacherDTO;
import com.github.angel.raa.modules.utils.api.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Validated
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService service;

    @GetMapping("/all-teacher")
    public ResponseEntity<List<TeacherDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/teacher-by/{id}")
    public ResponseEntity<TeacherDTO> findById(@Valid @PathVariable(value = "id") @Min(1) Long id) {
        return ResponseEntity.ok(service.findById(id));
    }



    @PostMapping("/add-teacher")
    public ResponseEntity<Response> addTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(teacherDTO));
    }
    @PutMapping("/update-teacher/{id}")
    public ResponseEntity<Response> updateTeacher(@NotNull(message = "null values not allowed") @Valid @PathVariable @Min(1) Long id, @RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok(service.update(id, teacherDTO));
    }
    @DeleteMapping("/delete-teacher/{id}")
    public ResponseEntity<Response> deleteTeacher(@Valid @PathVariable(value = "id") @Min(1) Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
