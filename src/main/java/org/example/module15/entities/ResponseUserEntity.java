package org.example.module15.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ResponseUserEntity {
    private User user;
    private String message;

    @Override
    public String toString() {
        return "ResponseUserEntity{" +
                "user=" + user +
                ", message='" + message + '\'' +
                '}';
    }
}

