package com.example.demo.repository.impl;

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

  public List<Todo> findAll() {
    return todoJpaRepository.findAll();
  }

  public Todo findById(Long id) {
    return todoJpaRepository.findById(id).orElse(null);
  }

  public Todo save(Todo todo) {
    return todoJpaRepository.save(todo);
  }


}
