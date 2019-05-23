package com.training.simple.todolist.dao.command.todoitem;

import com.training.simple.todolist.entity.ImmutableTodoItem;
import com.training.simple.todolist.entity.TodoItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class FindAllTodoItemsByTodoListCommand implements Function<Long, List<TodoItem>> {

    private static final String QUERY = "SELECT id, todo_list_id, description, created_at, done, done_at " +
            " FROM todo_item " +
            " WHERE todo_list_id = :todo_list_id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FindAllTodoItemsByTodoListCommand(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<TodoItem> apply(Long todoListId) {
        final Map<String, Long> parameters = new HashMap<>();

        parameters.put("todo_list_id", todoListId);

        return jdbcTemplate.query(QUERY, parameters, (rs, i) -> ImmutableTodoItem.builder()
                .id(rs.getLong("id"))
                .todoListId(rs.getLong("todo_list_id"))
                .description(rs.getString("description"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .done(rs.getBoolean("done"))
                .doneAt(Optional.ofNullable(getNullableLocalDateTime(rs, "done_at")))
                .build());
    }

    private LocalDateTime getNullableLocalDateTime(final ResultSet rs, final String field) throws SQLException {
        final Timestamp timestamp = rs.getTimestamp(field);

        if (timestamp != null) {
            return timestamp.toLocalDateTime();
        }

        return null;
    }
}
