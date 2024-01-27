package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.DTO.NotaDTO;
import com.github.angel.raa.modules.utils.api.Response;

import java.util.List;

public interface NotaService {
    List<NotaDTO> getNotaByStudent(Long studentId);
    Response save(Long studentId, Long courseId, int qua);
    Response delete(Long id);
    Response update(Long notaId, int qua);

}
