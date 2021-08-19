package com.example.todolist.integration;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
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



}
