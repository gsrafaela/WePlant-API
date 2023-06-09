package br.com.api.weplant.dto;

import br.com.api.weplant.entities.Comment;

public record CommentDTO(
        String body,

        String username,

        String date
) {

    public CommentDTO(Comment comment) {
        this(comment.getBody(), comment.getUser().getUsername(), comment.getDate().toString());
    }
}
