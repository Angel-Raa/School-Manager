package com.github.angel.raa.modules.utils.DTO;

import com.github.angel.raa.modules.persistence.models.Address;
import com.github.angel.raa.modules.utils.constants.Message;
import jakarta.validation.constraints.*;

public record StudentDTO(Long id,
                         @NotNull
                         @Size(min = 4, max = 30, message = "nombre no valido")
                         String name,
                         @NotNull
                         @NotEmpty(message = "Campo apellido no puede esta vacio")
                         @Size(min = 4, max = 30, message = "apellido no valido")
                         String surname,
                         @NotNull
                         @NotEmpty(message = "Campo email no puede esta vacio")
                         @Size(min = 4, max = 30, message = "email no valido")
                         @Email(message = "email no valido", regexp = Message.VALIDATE_EMAIL_REGEX)
                         @Size(min = 4, max = 30, message = "email no valido")
                         String email,
                         @NotNull
                         @Size(min = 10, message = "Campo no valido")
                         @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Campo no valido")
                         String phone,
                         String gender,
                         Address address



) {
}
