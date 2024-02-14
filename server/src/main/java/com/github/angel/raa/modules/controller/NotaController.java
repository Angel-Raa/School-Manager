package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.service.intefaces.NotaService;
import com.github.angel.raa.modules.utils.DTO.NotaDTO;
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
public class NotaController {
    private final NotaService service;

    @GetMapping("/all-nota")
    public ResponseEntity<List<NotaDTO>> getNotaByStudent() {
        return ResponseEntity.ok(service.getNotaByStudent());
    }


    @GetMapping("/nota-student/{studentId}")
    public ResponseEntity<List<NotaDTO>> getNotaByStudentId(@PathVariable(value = "studentId") @Min(1) @PositiveOrZero(message = "Id must be greater than 0") Long studentId) {
        return ResponseEntity.ok(service.getNotaByCourse(studentId));
    }
    @PostMapping("/save-nota/student/{studentId}/course/{courseId}")
    public ResponseEntity<Response> save(@Valid @PositiveOrZero(message = "Id must be greater than 0")  @PathVariable(value = "studentId") @Min(1) Long studentId, @PathVariable(value = "courseId") @Min(1) Long courseId,@RequestBody NotaDTO dto ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto, studentId, courseId));
    }

    @PutMapping("/update-nota/{id}")
    public ResponseEntity<Response> update(@Valid @PathVariable @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long id, @RequestBody NotaDTO dto ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }
    @DeleteMapping("/delete-nota/{id}")
    public ResponseEntity<Response> delete(@Valid @PathVariable @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
    }
}
