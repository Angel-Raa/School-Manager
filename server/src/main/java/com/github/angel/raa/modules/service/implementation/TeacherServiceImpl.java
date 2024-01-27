package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.configuration.exception.HandlerException;
import com.github.angel.raa.modules.configuration.exception.NotFoundTeacherException;
import com.github.angel.raa.modules.persistence.models.Address;
import com.github.angel.raa.modules.persistence.models.Teacher;
import com.github.angel.raa.modules.persistence.repository.TeacherRepository;
import com.github.angel.raa.modules.service.intefaces.TeacherService;
import com.github.angel.raa.modules.utils.DTO.AddressDTO;
import com.github.angel.raa.modules.utils.DTO.TeacherDTO;
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
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository service;
    @Transactional(readOnly = true)
    @Override
    public List<TeacherDTO> findAll() {
        return service.findAll().stream()
                .map(it -> new TeacherDTO(it.getId(), it.getName(), it.getSurname(), it.getEmail(), it.getPhone(), it.getGender(),it.getSpecialization(), new AddressDTO(it.getAddress().getCity(), it.getAddress().getStreet())))
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public TeacherDTO findById(Long id) {
        return service.findById(id).map(this::mapTeacher).orElseThrow(() -> new NotFoundTeacherException(Message.NOT_FOUND_TEACHER, HttpStatus.NOT_FOUND, 4040 ));
    }
    @Transactional
    @Override
    public Response save(TeacherDTO teacherDTO) {
        try {
            Teacher teacher = mapTeacherDTO(teacherDTO);
            service.save(teacher);
            return Response.builder()
                    .message("Teacher saved successfully")
                    .code(Message.CREATED)
                    .status(HttpStatus.CREATED)
                    .timestamp(LocalDateTime.now())
                    .build();
        }catch (HandlerException err){
            return Response.builder()
                    .message(err.getMessage())
                    .code(err.getCode())
                    .status(HttpStatus.BAD_REQUEST)
                    .timestamp(err.getTimestamp())
                    .build();
        }

    }
    @Transactional
    @Override
    public Response delete(Long id) {
        Teacher teacher = service.findById(id).orElseThrow(() -> new NotFoundTeacherException(Message.NOT_FOUND_TEACHER, HttpStatus.NOT_FOUND, 4040 ));
        service.delete(teacher);
        return Response.builder()
                .timestamp(LocalDateTime.now())
                .message(Message.DELETE_SUCCESS)
                .code(Message.NO_CONTENT)
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    @Transactional
    @Override
    public Response update(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = service.findById(id).orElseThrow(() -> new NotFoundTeacherException(Message.NOT_FOUND_TEACHER, HttpStatus.NOT_FOUND, 4040 ));
        mapUpdateTeacher(teacherDTO, teacher);
        service.save(teacher);
        return Response.builder()
                .message(Message.UPDATE_SUCCESS)
                .status(HttpStatus.OK)
                .code(Message.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }



    @Contract("_ -> new")
    private @NotNull TeacherDTO mapTeacher(@NotNull Teacher teacher){
        return new TeacherDTO(teacher.getId(), teacher.getName(), teacher.getSurname(), teacher.getEmail(), teacher.getPhone(), teacher.getGender(), teacher.getSpecialization(), new AddressDTO(teacher.getAddress().getCity(), teacher.getAddress().getStreet()));
    }
    private @NotNull Teacher mapTeacherDTO(@NotNull TeacherDTO teacherDTO){
        Teacher teacher = new Teacher();
        Address address = new Address();
        address.setCity(teacherDTO.address().city());
        address.setStreet(teacherDTO.address().street());
        teacher.setId(teacherDTO.teacherId());
        teacher.setName(teacherDTO.name());
        teacher.setSurname(teacherDTO.surname());
        teacher.setEmail(teacherDTO.email());
        teacher.setPhone(teacherDTO.phone());
        teacher.setGender(teacherDTO.gender());
        teacher.setSpecialization(teacherDTO.specialization());
        teacher.setAddress(address);
        return teacher;
    }
    private void mapUpdateTeacher(TeacherDTO teacherDTO,  Teacher teacher) {
        teacher.setName(teacherDTO.name());
        teacher.setSurname(teacherDTO.surname());
        teacher.setEmail(teacherDTO.email());
        teacher.setPhone(teacherDTO.phone());
        teacher.setGender(teacherDTO.gender());
        teacher.setSpecialization(teacherDTO.specialization());
        teacher.setAddress(mapTeacherDTO(teacherDTO).getAddress());
    }
}
