package com.example.todolist.mapper;

import com.example.todolist.dto.TodoRequest;
import com.example.todolist.dto.TodoResponse;
import com.example.todolist.entity.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoMapper {
    public Todo toEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);

        return todo;
    }

    public TodoResponse toResponse(Todo todo) {
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        return todoResponse;
    }

    public List<TodoResponse> toResponse(List<Todo> todos) {
        List<TodoResponse> todoResponses = new ArrayList<>();

        for (Todo todo : todos) {
            TodoResponse todosResponse = new TodoResponse();
            BeanUtils.copyProperties(todo, todosResponse);
            todoResponses.add(todosResponse);
        }

        return todoResponses;
    }
}
