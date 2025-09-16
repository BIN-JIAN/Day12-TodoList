package com.example.demo.controller;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;
import com.example.demo.dto.TodoDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.findAllTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable long id) {
        Todo todo = todoService.findTodoById(id);
        return ResponseEntity.ok(todo);
    }
    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return ResponseEntity.created(null).body(new TodoDTO(createdTodo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable long id,@RequestBody TodoDTO todo) {
        Todo updatedTodo = todoService.updateTodo(id,todo);
        return ResponseEntity.ok(new TodoDTO(updatedTodo));
    }
}
