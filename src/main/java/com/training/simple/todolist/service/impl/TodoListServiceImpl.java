package com.training.simple.todolist.service.impl;

import com.training.simple.todolist.entity.TodoList;
import com.training.simple.todolist.repository.TodoListRepository;
import com.training.simple.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private Clock clock;

    @Autowired
    private TodoListRepository todoListRepository;

    @Override
    @Transactional
    public TodoList create(final String name) {
        final TodoList todoList = new TodoList();
        final LocalDateTime now = LocalDateTime.now(clock);

        todoList.setName(name);
        todoList.setCreationDate(now);

        todoListRepository.save(todoList);

        return todoList;
    }
}
