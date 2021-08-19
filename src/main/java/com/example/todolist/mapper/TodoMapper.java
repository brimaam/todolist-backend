package com.example.todolist.mapper;

import com.example.todolist.dto.TodoResponse;
import com.example.todolist.entity.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoMapper {
    public List<TodoResponse> toResponse(List<Todo> todos){
        List<TodoResponse> todoResponses = new ArrayList<>();

        for (Todo todo : todos) {
            TodoResponse todosResponse = new TodoResponse();
            BeanUtils.copyProperties(todo, todosResponse);
            todoResponses.add(todosResponse);
        }

        return todoResponses;
    }
}
