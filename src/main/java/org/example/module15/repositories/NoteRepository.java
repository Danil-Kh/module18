package org.example.module15.repositories;

import org.example.module15.entities.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    @Override
    Optional<Note> findById(Long aLong);
    @Query(nativeQuery = true, value = "SELECT * FROM notes n WHERE n.user_id = :userId")
    List<Note> getUserNotes(Long userId);
    @Query(nativeQuery = true, value = "SELECT id FROM notes n where n.user_id = :userId")
    List<Long> getUserNoteIds(Long userId);
}
