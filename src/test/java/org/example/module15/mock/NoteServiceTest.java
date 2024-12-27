package org.example.module15.mock;

import org.example.module15.entities.Note;
import org.example.module15.entities.User;
import org.example.module15.exceptions.UnableAddNote;
import org.example.module15.repositories.NoteRepository;
import org.example.module15.services.NoteService;
import org.example.module15.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @InjectMocks
    private NoteService noteService;

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private UserService userService;

    @Test
    void addNoteSuccessfullyTest() {

        String username = "testUser";
        User mockUser = new User();
        mockUser.setUsername(username);

        Note inputNote = new Note();
        inputNote.setTitle("Test Title");
        inputNote.setContent("Test Content");

        when(userService.findByUserName(username)).thenReturn(mockUser);


        noteService.addNote(inputNote, username);

        verify(userService).findByUserName(username);

        ArgumentCaptor<Note> noteCaptor = ArgumentCaptor.forClass(Note.class);
        verify(noteRepository).save(noteCaptor.capture());

        Note savedNote = noteCaptor.getValue();
        assertNotNull(savedNote);
        assertEquals(mockUser, savedNote.getUser());
        assertEquals("Test Title", savedNote.getTitle());
        assertEquals("Test Content", savedNote.getContent());
    }

    @Test
    void addNoteThrowsExceptionWhenTitleIsBlank() {
        Note inputNote = new Note();
        inputNote.setTitle("");
        inputNote.setContent("Test Content");

        UnableAddNote exception = assertThrows(
                UnableAddNote.class, () -> noteService.addNote(inputNote, "testUser")
        );
        assertEquals("Note title is blank", exception.getMessage());


        verifyNoInteractions(noteRepository);
    }
}