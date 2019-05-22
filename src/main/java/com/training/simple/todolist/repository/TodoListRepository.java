package com.training.simple.todolist.repository;

import com.training.simple.todolist.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
