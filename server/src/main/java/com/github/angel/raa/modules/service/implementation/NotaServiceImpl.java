package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.configuration.exception.HandlerException;
import com.github.angel.raa.modules.configuration.exception.NotFoundNotaException;
import com.github.angel.raa.modules.configuration.exception.NotFoundStundetException;
import com.github.angel.raa.modules.configuration.exception.NotFoundSubjectsException;
import com.github.angel.raa.modules.persistence.models.Course;
import com.github.angel.raa.modules.persistence.models.Nota;
import com.github.angel.raa.modules.persistence.models.Student;
import com.github.angel.raa.modules.persistence.repository.CourseRepository;
import com.github.angel.raa.modules.persistence.repository.NotaRepository;
import com.github.angel.raa.modules.persistence.repository.StudentRepository;
import com.github.angel.raa.modules.service.intefaces.NotaService;
import com.github.angel.raa.modules.utils.DTO.NotaDTO;
import com.github.angel.raa.modules.utils.api.Response;
import com.github.angel.raa.modules.utils.constants.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Log4j2
@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {
    private final NotaRepository notaRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    @Transactional(readOnly = true)
    @Override
    public List<NotaDTO> getNotaByStudent() {
        log.info("Getting all notas");
        return notaRepository.findAll().stream()
                .map(it -> new NotaDTO(it.getId(), it.getStudent().getName(), it.getQualification())
                ).toList();
    }

    @Transactional
    @Override
    public Response save(NotaDTO dto,Long studentId, Long courseId) {
        try {
            Nota nota = new Nota();
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundStundetException("Student not found", HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP, LocalDateTime.now()));
             Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundSubjectsException("Subject not found", HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
            nota.setStudent(student);
            nota.setCourse(course);
            nota.setQualification(dto.qualification());
            notaRepository.save(nota);
            return Response.builder()
                    .message("Nota added successfully")
                    .code(Message.CREATED)
                    .status(HttpStatus.CREATED)
                    .timestamp(LocalDateTime.now())
                    .build();


        } catch (HandlerException e) {
            throw  new HandlerException(e.getMessage(), e.getStatus(), e.getCode(), e.getTimestamp());
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
    public Response update(Long notaId, Long qualification) {
        Nota nota = notaRepository.findById(notaId).orElseThrow(() -> new NotFoundNotaException("Nota not found", HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
        nota.setQualification(qualification);
        return Response.builder()
                .message(Message.UPDATE_SUCCESS)
                .code(Message.BAD_REQUEST)
                .build();
    }
}
