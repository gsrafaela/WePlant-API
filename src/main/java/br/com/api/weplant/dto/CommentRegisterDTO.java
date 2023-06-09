package br.com.api.weplant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Calendar;

@AllArgsConstructor
@Data
@Builder
public class CommentRegisterDTO{

        @NotBlank @NotNull
        private Long userId;

        @NotBlank @NotNull @Length(min = 1, max = 200)
        private String body;

        private Calendar date;
}
