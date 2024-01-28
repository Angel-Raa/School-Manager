package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.configuration.exception.HandlerException;
import com.github.angel.raa.modules.configuration.exception.NotFoundNotaException;
import com.github.angel.raa.modules.configuration.exception.NotFoundStundetException;
import com.github.angel.raa.modules.configuration.exception.NotFoundSubjectsException;
import com.github.angel.raa.modules.persistence.models.Nota;
import com.github.angel.raa.modules.persistence.models.Student;
import com.github.angel.raa.modules.persistence.models.Subjects;
import com.github.angel.raa.modules.persistence.repository.NotaRepository;
import com.github.angel.raa.modules.persistence.repository.StudentRepository;
import com.github.angel.raa.modules.persistence.repository.SubjectsRepository;
import com.github.angel.raa.modules.service.intefaces.NotaService;
import com.github.angel.raa.modules.utils.DTO.NotaDTO;
import com.github.angel.raa.modules.utils.api.Response;
import com.github.angel.raa.modules.utils.constants.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {
    private final NotaRepository notaRepository;
    private final StudentRepository studentRepository;
    private final SubjectsRepository subjectsRepository;

    @Transactional(readOnly = true)
    @Override
    public List<NotaDTO> getNotaByStudent() {
        return notaRepository.findAll().stream()
                .map(it -> new NotaDTO(it.getId(), it.getStudent().getId(), it.getSubject().getId(), it.getQualification())
                ).toList();
    }

    @Transactional
    @Override
    public Response save(NotaDTO dto) {
        try {
            Nota nota = new Nota();
            Student student = studentRepository.findById(dto.studentId()).orElseThrow(() -> new NotFoundStundetException("Student not found", HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP, LocalDateTime.now()));
            Subjects subject = subjectsRepository.findById(dto.subjectId()).orElseThrow(() -> new NotFoundSubjectsException("Subject not found", HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
            nota.setStudent(student);
            nota.setSubject(subject);
            nota.setQualification(dto.qualification());
            notaRepository.save(nota);
            return Response.builder()
                    .message("Nota added successfully")
                    .code(Message.CREATED)
                    .status(HttpStatus.CREATED)
                    .timestamp(LocalDateTime.now())
                    .build();


        } catch (HandlerException e) {
            return Response.builder()
                    .message(e.getMessage())
                    .code(e.getCode())
                    .status(e.getStatus())
                    .build();
        }
    }
    @Transactional
    @Override
    public Response delete(Long id) {
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new NotFoundNotaException("Nota not found", HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
        notaRepository.delete(nota);
        return Response.builder()
                .message("Nota deleted successfully")
                .code(Message.NO_CONTENT)
                .status(HttpStatus.NO_CONTENT)
                .timestamp(LocalDateTime.now())
                .build();
    }
    @Transactional
    @Override
    public Response update(Long notaId, int qualification) {
        Nota nota = notaRepository.findById(notaId).orElseThrow(() -> new NotFoundNotaException("Nota not found", HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
        nota.setQualification(qualification);
        return Response.builder()
                .message(Message.UPDATE_SUCCESS)
                .code(Message.BAD_REQUEST)
                .build();
    }
}
