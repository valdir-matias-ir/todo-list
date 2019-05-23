package com.training.simple.todolist.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.Optional;

@Value.Immutable
@JsonDeserialize(as = ImmutableTodoItem.class)
@JsonSerialize(as = ImmutableTodoItem.class)
public interface TodoItem {

    Optional<Long> id();

    Long todoListId();

    String description();

    LocalDateTime createdAt();

    @Value.Default
    default boolean done() {
        return false;
    };

    Optional<LocalDateTime> doneAt();
}
