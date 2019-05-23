package com.training.simple.todolist.dao.command.todoitem;

import com.training.simple.todolist.entity.TodoItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UpdateTodoItemCommand implements Function<TodoItem, Boolean> {

    private static final String UPDATE = "UPDATE todo_item " +
                    " SET todo_list_id = :todo_list_id, description = :description, done = :done, done_at = :done_at" +
                    " WHERE id = :id ";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UpdateTodoItemCommand(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Boolean apply(final TodoItem todoItem) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("todo_list_id", todoItem.todoListId());
        parameters.put("description", todoItem.description());
        parameters.put("done", todoItem.done());
        parameters.put("done_at", todoItem.doneAt().orElse(null));
        parameters.put("id", todoItem.id().orElse(-1L));

        final int totalUpdated = jdbcTemplate.update(UPDATE, parameters);

        return totalUpdated > 0;
    }
}
