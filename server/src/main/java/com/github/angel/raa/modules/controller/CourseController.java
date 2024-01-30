package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.service.intefaces.CourseService;
import com.github.angel.raa.modules.utils.DTO.CourseDTO;
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

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/nota")
@CrossOrigin(origins = "*")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/all-course")
    public ResponseEntity<List<CourseDTO>> getAllCourse() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @PostMapping("/save-course/{subjectsId}")
    public ResponseEntity<Response> create(@Valid @RequestBody CourseDTO dto, @PathVariable(value = "subjectsId")  @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long subjectsId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(subjectsId, dto));
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
