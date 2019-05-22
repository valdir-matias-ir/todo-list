package com.training.simple.todolist.repository;

import com.training.simple.todolist.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long>, TodoItemRepositoryCustom {
}
