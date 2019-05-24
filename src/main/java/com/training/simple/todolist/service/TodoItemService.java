package com.training.simple.todolist.service;

import com.training.simple.todolist.entity.TodoItem;

public interface TodoItemService {

    TodoItem create(Long todoListId, String description);

    TodoItem setDone(Long todoListId, Long id);

    TodoItem setUndone(Long todoListId, Long id);
}
