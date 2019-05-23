package com.training.simple.todolist.dao.command.todolist;

import com.training.simple.todolist.entity.ImmutableTodoList;
import com.training.simple.todolist.entity.TodoList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class AddTodoListCommand implements Function<TodoList, TodoList> {

    private static final String INSERT = "INSERT INTO todo_list (name, created_at) VALUES (:name, :created_at)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AddTodoListCommand(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public TodoList apply(final TodoList todoList) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", todoList.name());
        parameters.put("created_at", todoList.createdAt());

        jdbcTemplate.update(INSERT, new MapSqlParameterSource(parameters), keyHolder);

        final Map<String, Object> generatedKeys = keyHolder.getKeys();

        return ImmutableTodoList.builder()
                .from(todoList)
                .id((Long) Objects.requireNonNull(generatedKeys).get("id"))
                .build();
    }
}
