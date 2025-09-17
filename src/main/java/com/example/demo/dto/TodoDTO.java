package com.example.demo.dto;

import com.example.demo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {

  private String text;
  private Boolean done;

  public TodoDTO(Todo createdTodo) {
    this.text = createdTodo.getText();
    this.done = createdTodo.getDone();
  }

  public Boolean isDone() {
    return done;
  }
}
