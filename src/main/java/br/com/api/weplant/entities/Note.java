package br.com.api.weplant.entities;

import br.com.api.weplant.dto.NoteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_wp_note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String body;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Garden garden;

    @Column(name = "note_date", nullable = false)
    private Calendar date;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Note(NoteDTO noteDTO) {
        this.body = noteDTO.getBody();
        this.date = Calendar.getInstance();
    }
  
}
