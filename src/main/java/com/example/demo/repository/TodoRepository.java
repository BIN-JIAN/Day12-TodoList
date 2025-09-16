package com.example.demo.repository;

import com.example.demo.entity.Todo;
import com.example.demo.dto.TodoDTO;
import java.util.List;


public interface TodoRepository {

  List<Todo> findAll();

  Todo save(TodoDTO todo);

  Todo findById(Long id);


}
