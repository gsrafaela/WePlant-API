package br.com.api.weplant.dto;

import org.springframework.validation.FieldError;

public record RestValidationError(String field, String message) {

    public RestValidationError(FieldError e) {
        this(e.getField(), e.getDefaultMessage());

    }

}
