package com.github.angel.raa.modules.service.intefaces;

import com.github.angel.raa.modules.utils.DTO.NotaDTO;
import com.github.angel.raa.modules.utils.api.Response;

import java.util.List;

public interface NotaService {
    List<NotaDTO> getNotaByStudent();
    Response save(NotaDTO dto,Long studentId, Long courseId);
    Response delete(Long id);
    Response update(Long notaId,  NotaDTO dto );
    List<NotaDTO> getNotaByCourse(Long studentId); //<-- returns a list of NotaDTO, not a set<NotaDTO>

}
