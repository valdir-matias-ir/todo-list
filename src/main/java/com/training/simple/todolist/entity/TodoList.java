package com.training.simple.todolist.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.Optional;

@Value.Immutable
@JsonDeserialize(as = ImmutableTodoList.class)
@JsonSerialize(as = ImmutableTodoList.class)
public interface TodoList {

    Optional<Long> id();

    String name();

    LocalDateTime createdAt();
}
