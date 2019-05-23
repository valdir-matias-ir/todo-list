package com.training.simple.todolist.dao.command.todoitem;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DeleteAllTodoItemsByTodoListCommand implements Consumer<Long> {

    private static final String DELETE = "DELETE FROM todo_item WHERE todo_list_id = :todo_list_id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DeleteAllTodoItemsByTodoListCommand(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(final Long todoListId) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("todo_list_id", todoListId);

        jdbcTemplate.update(DELETE, parameters);
    }
}
