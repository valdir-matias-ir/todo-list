package com.training.simple.todolist.service.impl;

import com.training.simple.todolist.entity.TodoItem;
import com.training.simple.todolist.entity.TodoList;
import com.training.simple.todolist.repository.TodoItemRepository;
import com.training.simple.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    @Autowired
    private Clock clock;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Override
    public TodoItem create(final TodoList todoList, final String description) {
        final TodoItem todoItem = new TodoItem();
        final LocalDateTime now = LocalDateTime.now(clock);

        todoItem.setTodoList(todoList);
        todoItem.setCreationDate(now);
        todoItem.setDescription(description);

        todoItemRepository.save(todoItem);

        return todoItem;
    }
}
