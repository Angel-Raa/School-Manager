package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.configuration.exception.Error;
import com.github.angel.raa.modules.configuration.exception.NotFoundStundetException;
import com.github.angel.raa.modules.persistence.models.Address;
import com.github.angel.raa.modules.persistence.models.Student;
import com.github.angel.raa.modules.persistence.repository.StudentRepository;
import com.github.angel.raa.modules.service.intefaces.StudentService;
import com.github.angel.raa.modules.utils.DTO.AddressDTO;
import com.github.angel.raa.modules.utils.DTO.StudentDTO;
import com.github.angel.raa.modules.utils.api.Response;
import com.github.angel.raa.modules.utils.constants.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Log
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @NotNull
    private static NotFoundStundetException getNotFoundStundetException() {
        return new NotFoundStundetException(Message.NOT_FOUND, HttpStatus.NOT_FOUND, Message.NOT_FOUND_HTTP, LocalDateTime.now());
    }


    @Transactional(readOnly = true)
    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(it -> new StudentDTO(it.getId(), it.getName(), it.getSurname(), it.getEmail(), it.getPhone(), it.getGender(), new AddressDTO(it.getAddress().getCity(), it.getAddress().getStreet())))
                .toList();
    }


    @Transactional
    @Override
    public Optional<StudentDTO> getStudentByEmail(@NotNull String email) {
        return Optional.empty();
    }
    @Transactional
    @Override
    public Optional<StudentDTO> getStudentByName(@NotNull String name) {
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public StudentDTO getStudentById(@NotNull Long id) {
        log.info("Get student by in");
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentServiceImpl::getNotFoundStundetException);
        return mapStudent(student);
    }

    @Transactional
    @Override
    public Response addStudent(StudentDTO studentDTO) {
        log.info(studentDTO.email());
        log.info("add student");

        try {
            Student student = mapStudent(studentDTO);
            studentRepository.save(student);
            return Response.builder()
                    .message("student add are success")
                    .code(Message.CREATED)
                    .status(HttpStatus.CREATED)
                    .timestamp(LocalDateTime.now())
                    .build();

        } catch (Error e) {
            log.info("Error adding student : {} ");
            return Response.builder()
                    .message("Failed to add student")
                    .code(Message.BAD_REQUEST)
                    .status(HttpStatus.BAD_REQUEST)
                    .timestamp(LocalDateTime.now())
                    .build();
        }
    }


    @Transactional
    @Override
    public Response deleteStudent( Long id) {
        try {
          Student student =  getStudentOrThrow(id);
          studentRepository.delete(student);
            return Response.builder()
                    .message("Student deleted successfully")
                    .code(Message.OK)
                    .status(HttpStatus.OK)
                    .timestamp(LocalDateTime.now())
                    .build();
        }catch (Error e) {
            return Response.builder()
                    .message("Cannot delete students")
                    .code(Message.BAD_REQUEST)
                    .status(HttpStatus.BAD_REQUEST)
                    .timestamp(LocalDateTime.now())
                    .build();
        }

    }

    private Student getStudentOrThrow( Long id) {
        return studentRepository.findById(id)
                .orElseThrow(StudentServiceImpl::getNotFoundStundetException);
    }

    @Transactional
    @Override
    public Response updateStudent( Long id,  StudentDTO studentDTO ) {
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentServiceImpl::getNotFoundStundetException);

        mapStudent(studentDTO, student);
        studentRepository.save(student);
        return Response.builder()
                .message("Student updated successfully")
                .code(Message.OK)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }

    private Student mapStudent(@NotNull StudentDTO studentDTO) {
        Student student = new Student();
        Address address = new Address();
        address.setCity(studentDTO.address().city());
        address.setStreet(studentDTO.address().street());

        student.setName(studentDTO.name());
        student.setSurname(studentDTO.surname());
        student.setEmail(studentDTO.email());
        student.setPhone(studentDTO.phone());
        student.setGender(studentDTO.gender());
        student.setAddress(address);
        log.info(student.toString());
        return student;
    }

    private StudentDTO mapStudent(Student it) {
        return new StudentDTO(
                it.getId(),
                it.getName(),
                it.getSurname(),
                it.getEmail(),
                it.getPhone(),
                it.getGender(),
                new AddressDTO(it.getAddress().getCity(), it.getAddress().getStreet()
                ));
    }

    private static void mapStudent(StudentDTO studentDTO, @NotNull Student student) {
        Address address = new Address();
        address.setCity(studentDTO.address().city());
        address.setStreet(studentDTO.address().street());
        student.setName(studentDTO.name());
        student.setSurname(studentDTO.surname());
        student.setEmail(studentDTO.email());
        student.setPhone(studentDTO.phone());
        student.setGender(studentDTO.gender());
        student.setAddress(address);
    }
}
