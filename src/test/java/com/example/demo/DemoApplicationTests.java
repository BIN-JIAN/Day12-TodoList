package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.entity.Todo;
import com.example.demo.repository.dao.TodoJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private TodoJpaRepository TodoJpaRepository;
  @AfterEach
  void cleanDatabase() {
    TodoJpaRepository.deleteAll();
  }

  @Test
  void should_list_all_todo_when_none_todo_then_return_empty() throws Exception {
    mockMvc.perform(get("/todos")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void shoule_list_one_todo_when_one_todo_then_return_one() throws Exception {

    String requestBody = """
                 {
                    "text":"first todo",
                    "done":false
      
                   }
      """;
    mockMvc.perform(post("/todos")
      .contentType(MediaType.APPLICATION_JSON)
      .content(requestBody));

    mockMvc.perform(get("/todos/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
      .andExpect(jsonPath("$.text").value("first todo"))
      .andExpect(jsonPath("$.done").value(false));

  }

  @Test
  void should_create_todo_when_post_todo_then_return_created() throws Exception {
    String requestBody = """
                 {
                    "text":"second todo",
                    "done":false
      
                   }
      """;

    mockMvc.perform(post("/todos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(status().isCreated())
      .andExpect(jsonPath("$.text").value("second todo"))
      .andExpect(jsonPath("$.done").value(false));
  }

  @Test
  void should_create_todo_when_text_is_empty_then_return_422() throws Exception {
    String requestBody = """
                 {
                    "text":"",
                    "done":false
      
                   }
      """;

    mockMvc.perform(post("/todos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
      .andExpect(status().isUnprocessableEntity());
  }


  @Test
  void should_put_todo_when_id_invalid_then_return_404() throws Exception {
    String requestBody = """
                 {
                    "text":"third todo",
                    "done":false
      
                   }
      """;
    ResultActions perform = mockMvc.perform(post("/todos")
      .contentType(MediaType.APPLICATION_JSON)
      .content(requestBody));

    String updateRequestBody = """
                 {
                    "text":"third todo updated",
                    "done":true
      
                   }
      """;
    mockMvc.perform(put("/todos/{id}", 99)
        .contentType(MediaType.APPLICATION_JSON)
        .content(updateRequestBody))
      .andExpect(status().isNotFound());

  }



  @Test
  void should_delete_todo_when_id_invalid_then_return_404() throws Exception {
    String requestBody = """
                 {
                    "text":"fourth todo",
                    "done":false
      
                   }
      """;
    ResultActions perform = mockMvc.perform(post("/todos")
      .contentType(MediaType.APPLICATION_JSON)
      .content(requestBody));

    MvcResult mvcResult = perform.andReturn();
    String responseBody = mvcResult.getResponse().getContentAsString();
    ObjectMapper objectMapper = new ObjectMapper();
    Todo createdTodo = objectMapper.readValue(responseBody, Todo.class);

    mockMvc.perform(get("/todos/{id}", createdTodo.getId())
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());

    mockMvc.perform(post("/todos/{id}/delete", 99)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound());

  }





}
