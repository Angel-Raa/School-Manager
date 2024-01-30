package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.service.intefaces.SubjectsService;
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

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins = "*")
public class SubjectsController {
    private final SubjectsService service;

    @GetMapping("/all-subjects")
    public ResponseEntity<List<SubjectsDTO>> getAllSubjects() {
        return ResponseEntity.ok(service.getAllSubjects());
    }

    @GetMapping("/get-subject-by-id/{id}")
    public ResponseEntity<SubjectsDTO> getSubjectById(@Valid @PathVariable @Min(1) @PositiveOrZero(message = "Id must be greater than 0") Long id) {
        return ResponseEntity.ok(service.getSubjectById(id));
    }

    @PostMapping("/add-subject")
    public ResponseEntity<Response> addSubject(@Valid @RequestBody SubjectsDTO subjectsDTO) {
        return ResponseEntity.status(201).body(service.createSubject(subjectsDTO));
    }

    @PutMapping("/update-subject/{id}")
    public ResponseEntity<Response> updateSubject(@Valid @PathVariable(value = "id") @Min(1) Long id, @RequestBody SubjectsDTO subjectsDTO) {
        return ResponseEntity.ok(service.updateSubject(id, subjectsDTO));
    }

    @DeleteMapping("/delete-subject/{id}")
    public ResponseEntity<Response> deleteSubject(@Valid @PathVariable(value = "id") @Min(1) @PositiveOrZero(message = "Id must be greater than 0") Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.deleteSubject(id));
    }
}
