package br.com.api.weplant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.api.weplant.dto.GardenDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tb_wp_garden")
@Check(constraints = "status in ('PLANTACAO', 'REGAR', 'CRESCIMENTO','COLHEITA')")
@Check(constraints = "type in ('V','H')")
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "garden_name", length = 50, nullable = false)
    private String name;

    @Column(nullable = false,length = 15)
    private String status;

    @Column(nullable = false,length = 50)
    private String plant;

    @Column(length = 1, nullable = false)
    private String type;//Pode ser V (Vertical) ou H (Horizontal)

    @OneToMany(mappedBy = "garden")
    private List<Note> notes;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Garden(GardenDTO gardenRegister) {
        this.name = gardenRegister.name();
        this.status = gardenRegister.status();
        this.plant = gardenRegister.plant();
        this.type = gardenRegister.type();
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

}
