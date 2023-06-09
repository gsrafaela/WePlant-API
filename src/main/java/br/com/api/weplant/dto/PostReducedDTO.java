package br.com.api.weplant.dto;

import br.com.api.weplant.entities.Post;

public record PostReducedDTO(

        String image,

        String description,

        String date,

        String userName,

        String username

) {

    public PostReducedDTO(Post post) {
        this(post.getImage(), post.getDescription(), post.getDate().toString(), post.getUser().getName(), post.getUser().getUsername());
    }
}
