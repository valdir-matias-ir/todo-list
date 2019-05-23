package com.training.simple.todolist.dao.impl;

import com.training.simple.todolist.dao.TodoItemDao;
import com.training.simple.todolist.dao.command.todoitem.*;
import com.training.simple.todolist.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TodoItemDaoImpl implements TodoItemDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoItemDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TodoItem> findAll(Long todoListId) {
        return new FindAllTodoItemsByTodoListCommand(jdbcTemplate).apply(todoListId);
    }

    @Override
    public Optional<TodoItem> findById(Long todoListId, Long id) {
        return new FindTodoItemByIdCommand(jdbcTemplate, todoListId).apply(id);
    }

    @Override
    public TodoItem save(final TodoItem todoItem) {
        return new AddTodoItemCommand(jdbcTemplate).apply(todoItem);
    }

    @Override
    public boolean update(final TodoItem todoItem) {
        return new UpdateTodoItemCommand(jdbcTemplate).apply(todoItem);
    }

    @Override
    public void delete(final Long todoListId, final Long id) {
        new DeleteTodoItemCommand(jdbcTemplate, todoListId).accept(id);
    }
}
