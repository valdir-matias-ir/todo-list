package com.training.simple.todolist.dao.command.todolist;

import com.training.simple.todolist.entity.ImmutableTodoList;
import com.training.simple.todolist.entity.TodoList;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class FindTodoListByIdCommand implements Function<Long, Optional<TodoList>> {

    private static final String QUERY = "SELECT id, name, created_at FROM todo_list WHERE id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FindTodoListByIdCommand(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Optional<TodoList> apply(final Long id) {
        final Map<String, Long> parameters = new HashMap<>();

        parameters.put("id", id);

        try {
            return jdbcTemplate.queryForObject(QUERY, parameters, (rs, i) -> Optional.of(ImmutableTodoList.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .build()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
