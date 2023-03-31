package com.project.autoKings.model.entity;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class CommentEntity extends Base{

    @Lob
    private String textContent;

    private LocalDateTime created;


    @ManyToOne
    private UserEntity author;


    @ManyToOne
    private MechanicEntity mechanic;

    public CommentEntity(){}

    public CommentEntity(String textContent, LocalDateTime created, UserEntity author, MechanicEntity mechanic) {
        this.textContent = textContent;
        this.created = created;
        this.author = author;
        this.mechanic = mechanic;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public MechanicEntity getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicEntity mechanic) {
        this.mechanic = mechanic;
    }
}
