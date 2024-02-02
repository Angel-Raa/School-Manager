package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.configuration.exception.HandlerException;
import com.github.angel.raa.modules.configuration.exception.NotFoundSubjectsException;
import com.github.angel.raa.modules.configuration.exception.NotFoundTeacherException;
import com.github.angel.raa.modules.persistence.models.Course;
import com.github.angel.raa.modules.persistence.models.Teacher;
import com.github.angel.raa.modules.persistence.repository.CourseRepository;
import com.github.angel.raa.modules.persistence.repository.TeacherRepository;
import com.github.angel.raa.modules.service.intefaces.CourseService;
import com.github.angel.raa.modules.utils.DTO.CourseDTO;
import com.github.angel.raa.modules.utils.DTO.CourseTeacherDTO;
import com.github.angel.raa.modules.utils.DTO.InfoDTO;
import com.github.angel.raa.modules.utils.api.Response;
import com.github.angel.raa.modules.utils.constants.Message;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;
    private final TeacherRepository teacherRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CourseDTO> findAll() {
        return repository.findAll().stream()
                .map(it -> new CourseDTO(it.getId(), it.getName(), it.getDescription()))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CourseDTO findById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public Response create(Long teacherId, CourseDTO courseDTO) {
        try {
            Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundTeacherException(Message.NOT_FOUND, HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
            Course course = new Course();
            course.setName(courseDTO.name());
            course.setDescription(courseDTO.description());
            course.setTeacher(teacher);
            repository.save(course);
            return Response.builder()
                    .status(HttpStatus.CREATED)
                    .message(Message.CREATE_SUCCESS_COURSE)
                    .code(Message.CREATED)
                    .timestamp(LocalDateTime.now())
                    .build();
        } catch (HandlerException e) {
            throw new HandlerException(e.getMessage(), e.getStatus(), e.getCode(), e.getTimestamp());
        }

    }
    @Transactional
    @Override
    public Response delete(Long courseId) {
        try {
            Course course = repository.findById(courseId).orElseThrow(() -> new NotFoundSubjectsException(Message.NOT_FOUND, HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
            repository.delete(course);
            return Response.builder()
                    .status(HttpStatus.NO_CONTENT)
                    .message(Message.DELETE_SUCCESS)
                    .code(Message.NO_CONTENT)
                    .timestamp(LocalDateTime.now())
                    .build();
        } catch (HandlerException e) {
            throw new HandlerException(e.getMessage(), e.getStatus(), e.getCode(), e.getTimestamp());
        }
    }
    @Transactional
    @Override
    public Response update(Long courseId, @NotNull CourseDTO courseDTO) {
        try {
            Course course = repository.findById(courseId).orElseThrow(() -> new NotFoundSubjectsException(Message.NOT_FOUND, HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP));
            course.setName(courseDTO.name());
            course.setDescription(courseDTO.description());
            repository.save(course);
            return Response.builder()
                    .status(HttpStatus.OK)
                    .message(Message.UPDATE_SUCCESS)
                    .code(Message.OK)
                    .timestamp(LocalDateTime.now())
                    .build();
        } catch (HandlerException e) {
            throw new HandlerException(e.getMessage(), e.getStatus(), e.getCode(), e.getTimestamp());
        }
    }
    @Transactional(readOnly = true)
    @Override
    public List<CourseTeacherDTO> getAllCourseTeachers() {
        return repository.findAll()
                .stream()
                .map(it -> new CourseTeacherDTO(it.getName(), mapCourse(it.getTeacher())))
                .toList();
    }

    @Contract("_ -> new")
    private @NotNull InfoDTO mapCourse(@NotNull Teacher teacher) {
        return new InfoDTO(teacher.getName(), teacher.getSurname(), teacher.getSpecialization());
    }
}
