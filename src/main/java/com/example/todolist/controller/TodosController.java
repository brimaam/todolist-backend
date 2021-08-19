package com.example.todolist.controller;

import com.example.todolist.dto.TodoResponse;
import com.example.todolist.mapper.TodoMapper;
import com.example.todolist.service.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodosController {
    @Autowired
    private final TodosService todosService;
    @Autowired
    private TodoMapper todoMapper;

    public TodosController(TodosService todosService) {
        this.todosService = todosService;
    }

    @GetMapping
    public List<TodoResponse> getTodos(){
        return todoMapper.toResponse(todosService.getTodos());
    }
}
