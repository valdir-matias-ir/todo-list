package com.training.simple.todolist.service;

import com.training.simple.todolist.entity.TodoItem;
import com.training.simple.todolist.entity.TodoList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoItemServiceTest {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private TodoItemService todoItemService;

    @Test
    public void testCreate_ShouldCreateATodoItem() {
        final TodoList todoList = givenTodoList();
        final TodoItem todoItem = todoItemService.create(todoList.id().orElse(-1L), "New TodoItem");

        Assert.assertNotNull(todoItem);
        Assert.assertNotNull(todoItem.id());
        Assert.assertNotNull(todoItem.todoListId());
        Assert.assertNotNull(todoItem.description());
        Assert.assertNotNull(todoItem.createdAt());
    }

    @Test(expected = Exception.class)
    public void testCreate_ShouldThrowException_WhenDescriptionIsNull() {
        final TodoList todoList = givenTodoList();
        todoItemService.create(todoList.id().orElse(-1L), null);
    }

    @Test
    public void setDone_ShouldSetTodoItemAsDone() {
        final TodoItem todoItem = givenUndoneTodoItem();
        final TodoItem updatedTodoItem = todoItemService.setDone(todoItem.todoListId(), todoItem.id().orElse(-1L));

        Assert.assertNotNull(updatedTodoItem);
        Assert.assertTrue(updatedTodoItem.done());
        Assert.assertNotNull(updatedTodoItem.doneAt());
    }

    @Test
    public void setUndone_ShouldSetITodoItemAsUndone() {
        final TodoItem todoItem = givenDoneTodoItem();
        final TodoItem updatedTodoItem = todoItemService.setUndone(todoItem.todoListId(), todoItem.id().orElse(-1L));

        Assert.assertNotNull(updatedTodoItem);
        Assert.assertFalse(updatedTodoItem.done());
        Assert.assertNull(updatedTodoItem.doneAt().orElse(null));
    }

    private TodoList givenTodoList() {
        return todoListService.create("Given TodoList");
    }

    private TodoItem givenUndoneTodoItem() {
        final TodoList todoList = givenTodoList();
        return todoItemService.create(todoList.id().orElse(-1L), "New TodoItem");
    }

    private TodoItem givenDoneTodoItem() {
        final TodoList todoList = givenTodoList();
        final TodoItem todoItem = todoItemService.create(todoList.id().orElse(-1L), "New TodoItem");
        return todoItemService.setDone(todoItem.todoListId(), todoItem.id().orElse(-1L));
    }
}