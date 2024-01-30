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


    @PostMapping("/save-nota")
    public ResponseEntity<Response> save(@Valid @RequestBody NotaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/update-nota/{id}")
    public ResponseEntity<Response> update(@Valid @PathVariable @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long id, @RequestBody Long qualification) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, qualification));
    }
    @DeleteMapping("/delete-nota/{id}")
    public ResponseEntity<Response> delete(@Valid @PathVariable @Min(1)  @PositiveOrZero(message = "Id must be greater than 0")  Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
    }
}