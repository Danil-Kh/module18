package org.example.module15;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Note {
    private Long id;
    private String title;
    private String content;
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
    @Override
    public String toString() {
        return title + ": " + content;
    }
}
