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
    void addNoteSuccessfullyTest() { //TODO: Разобрать тест на составляющие
        // Arrange: Setup mocks and input data
        String username = "testUser";
        User mockUser = new User();
        mockUser.setUsername(username);

        Note inputNote = new Note();
        inputNote.setTitle("Test Title");
        inputNote.setContent("Test Content");

        // Stub userService behavior
        when(userService.findByUserName(username)).thenReturn(mockUser); //TODO: Понят почему тут такое поведение

        // Act: Execute the method under test
        noteService.addNote(inputNote, username);

        // Assert: Verify interactions with mocks
        verify(userService).findByUserName(username);

        // Capture the argument passed to noteRepository.save()
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