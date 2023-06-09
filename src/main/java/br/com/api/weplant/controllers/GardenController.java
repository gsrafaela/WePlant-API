package br.com.api.weplant.controllers;

import br.com.api.weplant.dto.GardenDTO;
import br.com.api.weplant.dto.NoteDTO;
import br.com.api.weplant.entities.Garden;
import br.com.api.weplant.repositories.NoteRepository;
import br.com.api.weplant.services.GardenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/gardens")
public class GardenController {

    @Autowired
    private GardenService gardenService;

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<GardenDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new GardenDTO(gardenService.findById(id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gardenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid GardenDTO gardenDTO, @PathVariable Long id) {
        Garden garden = gardenService.fromDTO(gardenDTO);
        gardenService.update(garden, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/notes/add")
    public ResponseEntity<Void> insertNote(@RequestBody @Valid NoteDTO noteDTO, @PathVariable Long id) {
        gardenService.insertNote(noteDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/notes/{id}")
    public ResponseEntity<Void> updateNote(@PathVariable Long id, @RequestBody @Valid NoteDTO noteDTO) {
        gardenService.updateNote(noteDTO, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/notes")
    public ResponseEntity<List<NoteDTO>> findAllNotes(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ArrayList<>(gardenService.findAllNotes(id)).stream().map(NoteDTO::new).collect(Collectors.toList()));
    }

    @GetMapping(value = "/notes/{id}")
    public ResponseEntity<NoteDTO> findNoteById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new NoteDTO(gardenService.findNoteById(id)));
    }

}
