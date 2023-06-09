package br.com.api.weplant.dto;

import br.com.api.weplant.entities.Garden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record GardenDTO(

         @Size(min = 10, max = 30)
         String name,

         @NotBlank @NotNull
         String status,

         @NotBlank @NotNull
         String plant,

         @Length(max = 1) @NotNull @NotBlank
         String type
) {

    public GardenDTO(Garden garden) {
        this(garden.getName(), garden.getStatus(), garden.getPlant(), garden.getType());
    }
}
