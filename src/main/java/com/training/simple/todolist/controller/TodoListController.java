package com.training.simple.todolist.controller;

import com.training.simple.todolist.entity.TodoList;
import com.training.simple.todolist.exception.ResourceNotFoundException;
import com.training.simple.todolist.repository.TodoListRepository;
import com.training.simple.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class TodoListController {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoListService todoListService;

    @GetMapping("lists")
    public ResponseEntity<Page<TodoList>> list(@RequestParam(required = false, defaultValue = "0") final int page,
                                               @RequestParam(required = false, defaultValue = "10") final int size) {
        final Pageable pageable = PageRequest.of(page, size);
        final Page<TodoList> todoLists = todoListRepository.findAll(pageable);

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
        final Optional<TodoList> todoList = todoListRepository.findById(todoListId);

        return todoList.orElseThrow(ResourceNotFoundException::new);
    }
}
