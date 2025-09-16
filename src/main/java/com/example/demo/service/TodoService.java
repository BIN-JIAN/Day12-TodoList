package com.example.demo.service;

import com.example.demo.entity.Todo;
import com.example.demo.exception.InvalidIdException;
import com.example.demo.exception.InvalidTextException;
import com.example.demo.repository.TodoRepository;
import com.example.demo.dto.TodoDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;

  public List<Todo> findAllTodos() {
    return todoRepository.findAll();
  }
  public Todo findTodoById(Long id) {
    return todoRepository.findById(id);
  }

  public Todo createTodo(TodoDTO todo) {
    if(todo.getText()==null || todo.getText().isEmpty()){
      throw new InvalidTextException();
    }
    return todoRepository.save(todo);
  }

  public Todo updateTodo(Long id, TodoDTO todo) {
    if(todo.getText()==null || todo.getText().isEmpty()){
      throw new InvalidTextException();
    }
    if(todoRepository.findById(id) == null){
      throw new InvalidIdException();
    }
    return todoRepository.update(id, todo);
  }

  public ResponseEntity<Void> deleteTodo(Long id) {
    if(todoRepository.findById(id) == null){
      throw new InvalidIdException();
    }
    todoRepository.delete(id);
    return ResponseEntity.noContent().build();
  }
}
