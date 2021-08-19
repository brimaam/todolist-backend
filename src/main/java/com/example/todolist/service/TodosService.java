package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodosService {
    @Autowired
    private final TodoRepository todoRepository;

    public TodosService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo updateTodo, Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("To do not found."));

        todo.setDone(updateTodo.isDone());

        return todoRepository.save(todo);
    }
}
