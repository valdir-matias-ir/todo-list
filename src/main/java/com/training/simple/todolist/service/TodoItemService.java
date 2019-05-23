package com.training.simple.todolist.service;

import com.training.simple.todolist.entity.TodoItem;
import com.training.simple.todolist.entity.TodoList;

public interface TodoItemService {

    TodoItem create(Long todoListId, String description);

    TodoItem setDone(Long todoListId, Long id);

    TodoItem setUndone(Long todoListId, Long id);
}
