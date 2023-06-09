package br.com.api.weplant.entities;

import br.com.api.weplant.dto.PostReducedDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.Calendar;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tb_wp_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(name = "post_date", nullable = false)
    private Calendar date;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.MERGE)
    private List<Comment> comments;

    public Post(PostReducedDTO postReducedDTO) {
        this.image = postReducedDTO.image();
        this.description = postReducedDTO.description();
        this.date = Calendar.getInstance();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}
