package com.example.demo.service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import dto.TodoDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    return todoRepository.save(todo);
  }
}
