package com.training.simple.todolist.dao.command.todolist;

import com.training.simple.todolist.entity.ImmutableTodoList;
import com.training.simple.todolist.entity.TodoList;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.function.Supplier;

public class FindAllTodoListsCommand implements Supplier<List<TodoList>> {

    private static final String QUERY = "SELECT id, name, created_at FROM todo_list";

    private final JdbcTemplate jdbcTemplate;

    public FindAllTodoListsCommand(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TodoList> get() {
        return jdbcTemplate.query(QUERY, (rs, i) -> ImmutableTodoList.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build());
    }
}
