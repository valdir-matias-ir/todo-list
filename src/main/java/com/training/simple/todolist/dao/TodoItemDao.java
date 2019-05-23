package com.training.simple.todolist.dao;

import com.training.simple.todolist.entity.TodoItem;

import java.util.List;
import java.util.Optional;

public interface TodoItemDao {

    List<TodoItem> findAll(Long todoListId);

    Optional<TodoItem> findById(Long todoListId, Long id);

    TodoItem save(TodoItem todoItem);

    boolean update(TodoItem todoItem);

    void delete(Long todoListId, Long id);
}
