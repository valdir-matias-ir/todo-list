package com.training.simple.todolist.service.impl;

import com.training.simple.todolist.dao.TodoListDao;
import com.training.simple.todolist.entity.ImmutableTodoList;
import com.training.simple.todolist.entity.TodoList;
import com.training.simple.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private Clock clock;

    @Autowired
    private TodoListDao todoListDao;

    @Override
    public TodoList create(final String name) {
        final LocalDateTime now = LocalDateTime.now(clock);
        final ImmutableTodoList todoList = ImmutableTodoList.builder()
                .name(name)
                .createdAt(now)
                .build();

        return todoListDao.save(todoList);
    }
}
