package com.alura.forohub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String message;

    private LocalDateTime creationDate = LocalDateTime.now();

    @NotEmpty
    private String status;

    @NotEmpty
    private String author;

    @NotEmpty
    private String course;

    // Manually added getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public String getCourse() {
        return course;
    }

    // Manually added setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setId(Long id) {
        this.id = id;
    }
}