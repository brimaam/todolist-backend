package com.example.todolist.controller;

import com.example.todolist.dto.TodoRequest;
import com.example.todolist.dto.TodoResponse;
import com.example.todolist.mapper.TodoMapper;
import com.example.todolist.service.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<TodoResponse> getTodos() {
        return todoMapper.toResponse(todosService.getTodos());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TodoResponse addTodo(@RequestBody TodoRequest todoRequest) {
        return todoMapper.toResponse(todosService.addTodo(todoMapper.toEntity(todoRequest)));
    }

    @PutMapping(path = "/{todoId}")
    public TodoResponse updateTodo(@PathVariable Integer todoId, @RequestBody TodoRequest todoRequest) {
        return todoMapper.toResponse(todosService
                .updateTodo(todoMapper.toEntity(todoRequest), todoId));
    }

    @DeleteMapping(path = "/{todoId}")
    public void deleteTodo(@PathVariable Integer todoId) {
        todosService.deleteTodo(todoId);
    }

}
