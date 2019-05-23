package com.training.simple.todolist.dao;

import com.training.simple.todolist.entity.TodoList;

import java.util.List;
import java.util.Optional;

public interface TodoListDao {

    Optional<TodoList> findById(Long id);

    List<TodoList> findAll();

    TodoList save(TodoList todoList);

    void delete(Long id);
}
