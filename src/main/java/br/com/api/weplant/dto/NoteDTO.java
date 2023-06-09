package br.com.api.weplant.dto;

import br.com.api.weplant.entities.Note;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@AllArgsConstructor
@Data
@Builder
public class NoteDTO {

    @NotNull @NotBlank
    private String body;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Calendar date;

    public NoteDTO(Note note) {
        this.body = note.getBody();
        this.date = note.getDate();
    }

}
