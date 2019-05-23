package com.training.simple.todolist.dao.command.todoitem;

import com.training.simple.todolist.entity.ImmutableTodoItem;
import com.training.simple.todolist.entity.TodoItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class AddTodoItemCommand implements Function<TodoItem, TodoItem> {

    private static final String INSERT =
            "INSERT INTO todo_item (todo_list_id, description, created_at, done, done_at) " +
            " VALUES (:todo_list_id, :description, :created_at, FALSE, NULL)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AddTodoItemCommand(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public TodoItem apply(final TodoItem todoItem) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("todo_list_id", todoItem.todoListId());
        parameters.put("description", todoItem.description());
        parameters.put("created_at", todoItem.createdAt());

        jdbcTemplate.update(INSERT, new MapSqlParameterSource(parameters), keyHolder);

        final Map<String, Object> generatedKeys = keyHolder.getKeys();

        return ImmutableTodoItem.builder()
                .from(todoItem)
                .id((Long) Objects.requireNonNull(generatedKeys).get("id"))
                .build();
    }
}
