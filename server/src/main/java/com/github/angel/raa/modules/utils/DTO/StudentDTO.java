package com.github.angel.raa.modules.utils.DTO;

import jakarta.validation.constraints.*;

public record StudentDTO(Long id,
                         @NotEmpty(message = "Campo nombre no puede esta vacio")
                         @Size(min = 4,  message = "nombre no valido")
                         String name,
                         @NotEmpty(message = "Campo apellido no puede esta vacio")
                         @Size(min = 4,  message = "apellido no valido")
                         String surname,
                         @Email(message = "email no valido")
                         @NotEmpty(message = "Campo email no puede esta vacio")
                         String email,

                         @Size(min = 10, message = "Campo no valido")
                         @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Campo no valido")
                         String phone,
                         String gender,
                        AddressDTO address



) {
}
