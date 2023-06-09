package br.com.api.weplant.services;

import br.com.api.weplant.dto.GardenDTO;
import br.com.api.weplant.dto.NoteDTO;
import br.com.api.weplant.entities.Garden;
import br.com.api.weplant.entities.Note;
import br.com.api.weplant.entities.User;
import br.com.api.weplant.exceptions.NoDataFoundException;
import br.com.api.weplant.repositories.GardenRepository;
import br.com.api.weplant.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenService {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private NoteRepository noteRepository;

//    public List<Garden> findAll() {
//        return gardenRepository.findAll();
//    }

    public Garden findById(Long id) {
        return gardenRepository.findById(id).orElseThrow((() -> new NoDataFoundException("Garden with id " + id + " not found")));
    }

    public Garden insert(Garden garden) {
        return gardenRepository.save(garden);
    }

    public void update(Garden garden, Long id) {
        Garden gardenInDB = findById(id);
        dataUpdate(gardenInDB, garden);
        gardenRepository.save(gardenInDB);
        gardenRepository.flush();
    }

    public void deleteById(Long id) {
        gardenRepository.deleteById(id);
    }

    public List<Garden> findAllByUserId(Long id) {
        return gardenRepository.findAllByUserId(id);
    }

    public void insertNote(NoteDTO noteDTO, Long id) {
        Garden garden = gardenRepository.findById(id).get();
        Note note = new Note(noteDTO);
        note.setGarden(garden);
        note.setUser(garden.getUser());
        garden.addNote(note);
        gardenRepository.save(garden);
    }

    public void updateNote(NoteDTO noteDTO, Long noteId) {
        Note note = noteRepository.findById(noteId).get();
        note.setBody(noteDTO.getBody());
        noteRepository.save(note);
        noteRepository.flush();
    }

    public List<Note> findAllNotes(Long id) {
        return noteRepository.findAllByGardenId(id);
    }

    public Note findNoteById(Long id) {
        return noteRepository.findById(id).get();
    }

    private void dataUpdate(Garden gardenToAtt, Garden garden) {
        String name = (garden.getName() != null && garden.getName().isEmpty() && garden.getName().isBlank()) ? garden.getName() : gardenToAtt.getName();
        gardenToAtt.setName(name);

        String status = (garden.getStatus() != null && garden.getStatus().isEmpty() && garden.getStatus().isBlank()) ? garden.getStatus() : gardenToAtt.getStatus();
        gardenToAtt.setStatus(status);

        String plant = (garden.getPlant() != null && garden.getPlant().isEmpty() && garden.getPlant().isBlank()) ? garden.getPlant() : gardenToAtt.getPlant();
        gardenToAtt.setPlant(plant);

        String type = (garden.getType() != null && garden.getType().isBlank() && garden.getType().isEmpty()) ? garden.getType() : gardenToAtt.getType();
        gardenToAtt.setType(type);

    }

    public Garden fromDTO(GardenDTO gardenDTO) {
        return new Garden(gardenDTO);
    }

//    public User fromDTOResponse(UserNoProtectedDataDTO userNoProtectedDataDTO) {
//        return new User(userNoProtectedDataDTO);
//    }

}
