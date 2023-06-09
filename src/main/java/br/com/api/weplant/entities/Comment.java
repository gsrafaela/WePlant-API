package br.com.api.weplant.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_wp_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String body;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "comment_date", nullable = false)
    private Calendar date;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(String body, User user, Calendar date, Post post) {
        this.body = body;
        this.user = user;
        this.date = date;
        this.post = post;
    }
}
