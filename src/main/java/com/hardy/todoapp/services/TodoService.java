package com.hardy.todoapp.services;

import com.hardy.todoapp.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TodoService {
    Logger logger = LoggerFactory.getLogger(TodoService.class);
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount,"hamza","learn spring", LocalDate.now().plusWeeks(5),true));
        todos.add(new Todo(++todosCount,"hamza","learn docker", LocalDate.now().plusWeeks(10),true));
        todos.add(new Todo(++todosCount,"hamza","learn CI/CD", LocalDate.now().plusWeeks(10),true));
    }

    public List<Todo> findByUserName(String user){
        logger.info("Todo Service user is: "+user);

        return todos.stream().filter(t -> t.getUserName().equalsIgnoreCase(user)).collect(Collectors.toList());
    }

    public void addTodo(String userName, String description, LocalDate targetDate, boolean isDone){
      Todo todo =  new Todo(++todosCount,userName,description,targetDate,isDone);
        todos.add(todo);
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
