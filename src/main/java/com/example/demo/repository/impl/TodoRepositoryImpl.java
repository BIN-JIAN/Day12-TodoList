package com.example.demo.repository.impl;

import com.example.demo.dto.TodoDTO;
import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.dao.TodoJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

  @Autowired
  private TodoJpaRepository todoJpaRepository;

  @Override
  public List<Todo> findAll() {
    return todoJpaRepository.findAll();
  }

  @Override
  public Todo findById(Long id) {
    return todoJpaRepository.findById(id).orElse(null);
  }

  @Override
  public Todo save(TodoDTO todoDTO) {
    Todo todo = new Todo();
    todo.setText(todoDTO.getText());
    todo.setDone(todoDTO.isDone());
    return todoJpaRepository.save(todo);
  }

  @Override
  public Todo update(Long id, TodoDTO todoDTO) {
    Todo todo = todoJpaRepository.findById(id).orElse(null);
    if (todo != null) {
      todo.setText(todoDTO.getText());
      todo.setDone(todoDTO.isDone());
      todoJpaRepository.save(todo);
    }
    return todo;
  }

  @Override
  public void delete(Long id) {
    todoJpaRepository.deleteById(id);
  }

}
