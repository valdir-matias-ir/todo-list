package com.training.simple.todolist.controller;

import com.training.simple.todolist.dao.TodoListDao;
import com.training.simple.todolist.entity.TodoList;
import com.training.simple.todolist.exception.ResourceNotFoundException;
import com.training.simple.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class TodoListController {

    @Autowired
    private TodoListDao todoListDao;

    @Autowired
    private TodoListService todoListService;

    @GetMapping("lists")
    public ResponseEntity<List<TodoList>> list() {
        final List<TodoList> todoLists = todoListDao.findAll();

        return ResponseEntity.ok(todoLists);
    }

    @PostMapping("list")
    public ResponseEntity<TodoList> create(@RequestParam final String name) {
        final TodoList todoList = todoListService.create(name);

        return ResponseEntity.ok(todoList);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<TodoList> get(@PathVariable("id") final Long id) {
        final TodoList todoList = findTodoListOrThrow(id);

        return ResponseEntity.ok(todoList);
    }

    private TodoList findTodoListOrThrow(final Long todoListId) {
        final Optional<TodoList> todoList = todoListDao.findById(todoListId);

        return todoList.orElseThrow(ResourceNotFoundException::new);
    }
}
