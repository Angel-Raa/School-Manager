package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.configuration.exception.HandlerException;
import com.github.angel.raa.modules.configuration.exception.NotFoundSubjectsException;
import com.github.angel.raa.modules.configuration.exception.NotFoundTeacherException;
import com.github.angel.raa.modules.persistence.models.Subjects;
import com.github.angel.raa.modules.persistence.models.Teacher;
import com.github.angel.raa.modules.persistence.repository.SubjectsRepository;
import com.github.angel.raa.modules.persistence.repository.TeacherRepository;
import com.github.angel.raa.modules.service.intefaces.SubjectsService;
import com.github.angel.raa.modules.utils.DTO.SubjectsDTO;
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
public class SubjectsServiceImpl implements SubjectsService {
    private final SubjectsRepository repository;
    private final TeacherRepository teacherRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SubjectsDTO> getAllSubjects() {
        return repository.findAll().stream()
                .map(it -> new SubjectsDTO(it.getId(), it.getName(), it.getDescription(), it.getTeacher().getId()))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public SubjectsDTO getSubjectById(Long subjectsId) {
        return repository.findById(subjectsId).map(it -> new SubjectsDTO(it.getId(), it.getName(), it.getDescription(), it.getTeacher().getId())).orElseThrow(() -> new NotFoundSubjectsException(Message.NOT_FOUND, HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
    }

    @Transactional
    @Override
    public Response createSubject(SubjectsDTO subject) {
        Teacher teacher = teacherRepository.findById(subject.teacherId()).orElseThrow(() -> new NotFoundTeacherException(Message.NOT_FOUND_TEACHER, HttpStatus.NOT_FOUND, 4040));
        try {
            Subjects subjects = new Subjects();
            subjects.setDescription(subject.description());
            subjects.setName(subject.name());
            subjects.setTeacher(teacher);
            repository.save(subjects);
            return Response.builder()
                    .message("Subject created successfully")
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
    public Response updateSubject(Long subjectsId, SubjectsDTO subject) {
        try {
            Subjects subjects = repository.findById(subjectsId).orElseThrow(() -> new NotFoundSubjectsException(Message.NOT_FOUND, HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
            subjects.setName(subject.name());
            subjects.setDescription(subject.description());
            repository.save(subjects);
            return Response.builder()
                    .message("Subject updated successfully")
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

    @Override
    public Response deleteSubject(Long subjectsId) {
        Subjects subjects = repository.findById(subjectsId).orElseThrow(() -> new NotFoundSubjectsException(Message.NOT_FOUND, HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
        repository.delete(subjects);
        return Response.builder()
                .message(Message.DELETE_SUCCESS)
                .code(Message.NO_CONTENT)
                .status(HttpStatus.NO_CONTENT)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
