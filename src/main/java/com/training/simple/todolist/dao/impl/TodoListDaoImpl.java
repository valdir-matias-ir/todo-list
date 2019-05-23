package com.training.simple.todolist.dao.impl;

import com.training.simple.todolist.dao.TodoListDao;
import com.training.simple.todolist.dao.command.todolist.AddTodoListCommand;
import com.training.simple.todolist.dao.command.todolist.FindAllTodoListsCommand;
import com.training.simple.todolist.dao.command.todolist.FindTodoListByIdCommand;
import com.training.simple.todolist.entity.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TodoListDaoImpl implements TodoListDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoListDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<TodoList> findById(final Long id) {
        return new FindTodoListByIdCommand(jdbcTemplate).apply(id);
    }

    @Override
    public List<TodoList> findAll() {
        return new FindAllTodoListsCommand(jdbcTemplate).get();
    }

    @Override
    public TodoList save(final TodoList todoList) {
        return new AddTodoListCommand(jdbcTemplate).apply(todoList);
    }
}
