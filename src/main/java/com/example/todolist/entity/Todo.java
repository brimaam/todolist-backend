package com.example.todolist.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Todo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private boolean done;

    public Todo(Integer id, String text, boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
