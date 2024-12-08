package org.example.module15.repositories;

import org.example.module15.entities.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NoteRepository extends CrudRepository<Note, Long> {
    @Override
    Optional<Note> findById(Long aLong);
}
