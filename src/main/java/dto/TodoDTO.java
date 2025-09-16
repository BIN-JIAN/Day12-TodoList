package dto;

import com.example.demo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
  private String text;
  private Boolean completed;

  public TodoDTO(Todo createdTodo) {
    this.text = createdTodo.getText();
    this.completed = createdTodo.getCompleted();
  }

  public Boolean isCompleted() {
    return completed;
  }
}
