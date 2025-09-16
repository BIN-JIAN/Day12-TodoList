package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

  @Autowired
  private MockMvc mockMvc;

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
                    "completed":false
      
                   }
      """;
    mockMvc.perform(post("/todos")
      .contentType(MediaType.APPLICATION_JSON)
      .content(requestBody));

    mockMvc.perform(get("/todos/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
      .andExpect(jsonPath("$.text").value("first todo"))
      .andExpect(jsonPath("$.completed").value(false));

  }

  @Test
  void should_create_todo_when_post_todo_then_return_created() throws Exception {
    String requestBody = """
                 {
                    "text":"second todo",
                    "completed":false
      
                   }
      """;

    mockMvc.perform(post("/todos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(status().isCreated())
      .andExpect(jsonPath("$.text").value("second todo"))
      .andExpect(jsonPath("$.completed").value(false));
  }

  @Test
  void should_create_todo_when_text_is_empty_then_return_422() throws Exception {
    String requestBody = """
                 {
                    "text":"",
                    "completed":false
      
                   }
      """;

    mockMvc.perform(post("/todos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
      .andExpect(status().isUnprocessableEntity());
  }

}
