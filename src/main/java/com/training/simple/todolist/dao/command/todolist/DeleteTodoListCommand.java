package com.training.simple.todolist.dao.command.todolist;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DeleteTodoListCommand implements Consumer<Long> {

    private static final String DELETE = "DELETE FROM todo_list WHERE id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DeleteTodoListCommand(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(final Long id) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("id", id);

        jdbcTemplate.update(DELETE, parameters);
    }
}
