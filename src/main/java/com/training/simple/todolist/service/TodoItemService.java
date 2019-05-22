package com.training.simple.todolist.service;

import com.training.simple.todolist.entity.TodoItem;
import com.training.simple.todolist.entity.TodoList;

public interface TodoItemService {

    TodoItem create(TodoList todoList, String description);
}
