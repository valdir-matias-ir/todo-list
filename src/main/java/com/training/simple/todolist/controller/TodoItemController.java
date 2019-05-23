package com.training.simple.todolist.controller;

import com.training.simple.todolist.dao.TodoItemDao;
import com.training.simple.todolist.dao.TodoListDao;
import com.training.simple.todolist.entity.TodoItem;
import com.training.simple.todolist.exception.ResourceNotFoundException;
import com.training.simple.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("list/{listId}")
public class TodoItemController {

    @Autowired
    private TodoListDao todoListDao;

    @Autowired
    private TodoItemDao todoItemDao;

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("items")
    public ResponseEntity<List<TodoItem>> list(@PathVariable("listId") final Long todoListId) {
        final List<TodoItem> todoItems = todoItemDao.findAll(todoListId);

        return ResponseEntity.ok(todoItems);
    }

    @PostMapping("item")
    public ResponseEntity<TodoItem> create(@PathVariable("listId") final Long todoListId,
                                           @RequestParam final String description) {
        final TodoItem todoItem = todoItemService.create(todoListId, description);

        return ResponseEntity.ok(todoItem);
    }

    @GetMapping("item/{id}")
    public ResponseEntity<TodoItem> get(@PathVariable("listId") final Long todoListId,
                                        @PathVariable("id") final Long todoItemId) {
        final TodoItem todoItem = findTodoItemOrThrow(todoListId, todoItemId);

        return ResponseEntity.ok(todoItem);
    }

    @PostMapping("item/{id}/done")
    public ResponseEntity<TodoItem> done(@PathVariable("listId") final Long todoListId,
                                         @PathVariable("id") final Long todoItemId) {
        final TodoItem todoItem = todoItemService.setDone(todoListId, todoItemId);

        return ResponseEntity.ok(todoItem);
    }

    @PostMapping("item/{id}/undone")
    public ResponseEntity<TodoItem> undone(@PathVariable("listId") final Long todoListId,
                                           @PathVariable("id") final Long todoItemId) {
        final TodoItem todoItem = todoItemService.setUndone(todoListId, todoItemId);

        return ResponseEntity.ok(todoItem);
    }

    @DeleteMapping("item/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("listId") final Long todoListId,
                                          @PathVariable("id") final Long todoItemId) {
        todoItemDao.delete(todoListId, todoItemId);

        return ResponseEntity.ok(true);
    }

    private TodoItem findTodoItemOrThrow(final Long todoListId, final Long todoItemId) {
        final Optional<TodoItem> todoItem = todoItemDao.findById(todoListId, todoItemId);

        return todoItem.orElseThrow(ResourceNotFoundException::new);
    }
}
