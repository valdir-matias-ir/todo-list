package com.training.simple.todolist.dao.command.todoitem;

import com.training.simple.todolist.entity.ImmutableTodoItem;
import com.training.simple.todolist.entity.TodoItem;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class FindTodoItemByIdCommand implements Function<Long, Optional<TodoItem>> {

    private static final String QUERY = "SELECT id, todo_list_id, description, created_at, done, done_at " +
            " FROM todo_item " +
            " WHERE todo_list_id = :todo_list_id AND id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Long todoListId;

    public FindTodoItemByIdCommand(final JdbcTemplate jdbcTemplate, final Long todoListId) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.todoListId = todoListId;
    }

    @Override
    public Optional<TodoItem> apply(final Long id) {
        final Map<String, Long> parameters = new HashMap<>();

        parameters.put("todo_list_id", todoListId);
        parameters.put("id", id);

        try {
            return jdbcTemplate.queryForObject(QUERY, parameters, (rs, i) -> Optional.of(ImmutableTodoItem.builder()
                    .id(rs.getLong("id"))
                    .todoListId(rs.getLong("todo_list_id"))
                    .description(rs.getString("description"))
                    .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                    .done(rs.getBoolean("done"))
                    .doneAt(Optional.ofNullable(getNullableLocalDateTime(rs, "done_at")))
                    .build()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private LocalDateTime getNullableLocalDateTime(final ResultSet rs, final String field) throws SQLException {
        final Timestamp timestamp = rs.getTimestamp(field);

        if (timestamp != null) {
            return timestamp.toLocalDateTime();
        }

        return null;
    }
}
