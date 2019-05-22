package com.training.simple.todolist.repository;

import com.training.simple.todolist.entity.TodoItem;
import com.training.simple.todolist.entity.TodoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TodoItemRepositoryCustom {

    Page<TodoItem> findAllByTodoList(TodoList todoList, Pageable pageable);
    Optional<TodoItem> findByIdAndTodoListId(Long id, Long todoListId);
}
