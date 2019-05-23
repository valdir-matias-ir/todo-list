package com.training.simple.todolist.dao.command.todoitem;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DeleteTodoItemCommand implements Consumer<Long> {

    private static final String DELETE = "DELETE FROM todo_item WHERE todo_list_id = :todo_list_id AND id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Long todoListId;

    public DeleteTodoItemCommand(final JdbcTemplate jdbcTemplate, final Long todoListId) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.todoListId = todoListId;
    }

    @Override
    public void accept(final Long id) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("todo_list_id", todoListId);
        parameters.put("id", id);

        jdbcTemplate.update(DELETE, parameters);
    }
}
