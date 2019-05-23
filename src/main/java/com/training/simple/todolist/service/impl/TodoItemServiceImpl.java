package com.training.simple.todolist.service.impl;

import com.training.simple.todolist.dao.TodoItemDao;
import com.training.simple.todolist.entity.ImmutableTodoItem;
import com.training.simple.todolist.entity.TodoItem;
import com.training.simple.todolist.exception.ResourceNotFoundException;
import com.training.simple.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    @Autowired
    private Clock clock;

    @Autowired
    private TodoItemDao todoItemDao;

    @Override
    public TodoItem create(final Long todoListId, final String description) {
        final LocalDateTime now = LocalDateTime.now(clock);
        final ImmutableTodoItem todoItem = ImmutableTodoItem.builder()
                .todoListId(todoListId)
                .description(description)
                .createdAt(now)
                .build();

        return todoItemDao.save(todoItem);
    }

    @Override
    public TodoItem setDone(final Long todoListId, final Long id) {
        final Optional<TodoItem> optionalOldTodoItem = todoItemDao.findById(todoListId, id);
        final TodoItem oldTodoItem = optionalOldTodoItem.orElseThrow(ResourceNotFoundException::new);
        final LocalDateTime now = LocalDateTime.now(clock);

        final ImmutableTodoItem todoItem = ImmutableTodoItem.builder()
                .from(oldTodoItem)
                .done(true)
                .doneAt(now)
                .build();

        todoItemDao.update(todoItem);

        return todoItem;
    }

    @Override
    public TodoItem setUndone(final Long todoListId, final Long id) {
        final Optional<TodoItem> optionalOldTodoItem = todoItemDao.findById(todoListId, id);
        final TodoItem oldTodoItem = optionalOldTodoItem.orElseThrow(ResourceNotFoundException::new);

        final ImmutableTodoItem todoItem = ImmutableTodoItem.builder()
                .from(oldTodoItem)
                .done(false)
                .doneAt(Optional.empty())
                .build();

        todoItemDao.update(todoItem);

        return todoItem;
    }
}
