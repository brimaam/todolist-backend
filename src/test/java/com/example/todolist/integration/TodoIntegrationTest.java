package com.example.todolist.integration;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void deleteData(){
        todoRepository.deleteAll();
    }

    @Test
    void should_return_all_todos_when_call_get_todos_api() throws Exception {
        //given
        final Todo todo = new Todo("first to do item",false);
        todoRepository.save(todo);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value("first to do item"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    void should_add_todo_when_call_add_todo_api() throws Exception {
        //given
        String todo = "{\n" +
                "    \"text\": \"another to do item\",\n" +
                "    \"done\": false\n" +
                "}";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON).content(todo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("another to do item"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_update_todo_when_call_update_todo_api() throws Exception {
        //given
        final Todo todo = new Todo("first to do item",false);
        todoRepository.save(todo);
        Integer todoId = todo.getId();

        String todoUpdate = "{\n" +
                "    \"done\": true\n" +
                "}";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{todoId}", todoId)
                .contentType(MediaType.APPLICATION_JSON).content(todoUpdate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.done").value(true));
    }
    
    @Test
    void should_delete_todo_when_call_delete_todo_api() throws Exception {
        //given
        final Todo firstTodo = new Todo("first to do item",false);
        final Todo secondTodo = new Todo("second to do item",true);
        todoRepository.saveAll(Lists.list(firstTodo, secondTodo));
        Integer firstTodoId = firstTodo.getId();

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{firstTodoId}", firstTodoId))
                .andExpect(status().isOk());
    }
    
}
