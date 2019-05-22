package com.training.simple.todolist.controller;

import com.training.simple.todolist.entity.TodoItem;
import com.training.simple.todolist.entity.TodoList;
import com.training.simple.todolist.exception.ResourceNotFoundException;
import com.training.simple.todolist.repository.TodoItemRepository;
import com.training.simple.todolist.repository.TodoListRepository;
import com.training.simple.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("list/{listId}")
public class TodoItemController {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("items")
    public ResponseEntity<Page<TodoItem>> list(@PathVariable("listId") final Long todoListId,
                                               @RequestParam(required = false, defaultValue = "0") final int page,
                                               @RequestParam(required = false, defaultValue = "10") final int size) {
        final TodoList todoList = findTodoListOrThrow(todoListId);
        final Pageable pageable = PageRequest.of(page, size);
        final Page<TodoItem> todoItems = todoItemRepository.findAllByTodoList(todoList, pageable);

        return ResponseEntity.ok(todoItems);
    }

    @PostMapping("item")
    public ResponseEntity<TodoItem> create(@PathVariable("listId") final Long todoListId,
                                           @RequestParam final String description) {
        final TodoList todoList = findTodoListOrThrow(todoListId);
        final TodoItem todoItem = todoItemService.create(todoList, description);

        return ResponseEntity.ok(todoItem);
    }

    @PostMapping("item/{id}/done")
    public ResponseEntity<TodoItem> done(@PathVariable("listId") final Long todoListId,
                                         @PathVariable("id") final Long todoItemId) {
        final TodoItem todoItem = findTodoItemOrThrow(todoListId, todoItemId);

        return ResponseEntity.ok(todoItem);
    }

    private TodoList findTodoListOrThrow(final Long todoListId) {
        final Optional<TodoList> todoList = todoListRepository.findById(todoListId);

        return todoList.orElseThrow(ResourceNotFoundException::new);
    }

    private TodoItem findTodoItemOrThrow(final Long todoListId, final Long todoItemId) {
        final Optional<TodoItem> todoItem = todoItemRepository.findByIdAndTodoListId(todoItemId, todoListId);

        return todoItem.orElseThrow(ResourceNotFoundException::new);
    }
}
