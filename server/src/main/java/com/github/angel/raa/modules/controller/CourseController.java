package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.service.intefaces.CourseService;
import com.github.angel.raa.modules.utils.DTO.CourseDTO;
import com.github.angel.raa.modules.utils.DTO.CourseTeacherDTO;
import com.github.angel.raa.modules.utils.DTO.SubjectsDTO;
import com.github.angel.raa.modules.utils.api.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
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
@RequestMapping("/course")
@CrossOrigin(origins = "*")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/all-courses")
    public ResponseEntity<List<CourseDTO>> getAllCourse() {
        return ResponseEntity.ok(courseService.findAll());
    }
    @GetMapping("/all-courser-teacher")
    public ResponseEntity<List<CourseTeacherDTO>> getAllCourseTeachers() {
        return ResponseEntity.ok(courseService.getAllCourseTeachers());
    }

    @GetMapping("/subjects-teacher/{teacherId}")
    public ResponseEntity<List<SubjectsDTO>> getCourseTeachers(@PathVariable(value = "teacherId")  @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long teacherId) {
        return ResponseEntity.ok(courseService.getCourseTeachers(teacherId));
    }
    @PostMapping("/save-course/{teacherId}")
    public ResponseEntity<Response> create(@Valid @RequestBody CourseDTO dto, @PathVariable(value = "teacherId")  @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long teacherId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(teacherId, dto));
    }

    @PutMapping("/update-course/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody CourseDTO dto, @PathVariable(value = "id")  @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.update(id, dto));
    }

    @DeleteMapping("/delete-course/{id}")
    public ResponseEntity<Response> delete(@Valid @PathVariable @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courseService.delete(id));
    }
}
