package br.com.api.weplant.repositories;

import br.com.api.weplant.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("from Note n where n.user.id = :id")
    List<Note> findAllByGardenId(@Param("id") Long id);

}