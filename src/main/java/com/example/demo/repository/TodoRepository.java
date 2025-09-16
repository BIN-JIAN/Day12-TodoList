package com.example.demo.repository;

import com.example.demo.entity.Todo;
import java.util.List;
import org.springframework.stereotype.Repository;


public interface TodoRepository {

  List<Todo> findAll();

  Todo save(Todo todo);

  Todo findById(Long id);


}
