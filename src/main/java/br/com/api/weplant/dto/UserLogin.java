package br.com.api.weplant.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLogin(
        @NotBlank String username,
        @NotBlank String password) {

}
