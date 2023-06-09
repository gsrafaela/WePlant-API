package br.com.api.weplant.dto;

import java.time.LocalDate;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.api.weplant.entities.Address;
import br.com.api.weplant.entities.Phone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(

        @NotNull @NotBlank @NotNull
        String name,

        @NotNull @NotBlank
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthday,

        @NotNull @NotBlank
        String username,

        @NotNull @NotBlank
        String email,

        @NotNull @NotBlank
        String password,

        @NotNull @NotBlank
        Address address,

        @NotNull @NotBlank
        Phone phone
) {}
