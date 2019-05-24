package com.training.simple.todolist.service;

import com.training.simple.todolist.entity.TodoList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoListServiceTest {

    @Autowired
    private TodoListService todoListService;

    @Test
    public void testCreate_ShouldCreateATodoList() {
        final TodoList todoList = todoListService.create("New TodoList");

        Assert.assertNotNull(todoList);
        Assert.assertNotNull(todoList.id());
        Assert.assertNotNull(todoList.name());
        Assert.assertNotNull(todoList.createdAt());
    }

    @Test(expected = Exception.class)
    public void testCreate_ShouldThrowException_WhenNameIsNull() {
        todoListService.create(null);
    }
}