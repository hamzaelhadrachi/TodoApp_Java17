package com.hardy.todoapp.services;

import com.hardy.todoapp.models.Todo;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount,"Hamza","learn spring", LocalDate.now().plusWeeks(5),true));
        todos.add(new Todo(++todosCount,"Hamza","learn docker", LocalDate.now().plusWeeks(10),true));
        todos.add(new Todo(++todosCount,"Hamza","learn CI/CD", LocalDate.now().plusWeeks(10),true));
    }

    public List<Todo> findByUserName(String user){
        return todos;
    }

    public void addTodo(String userName, String description, LocalDate targetDate, boolean isDone){
      Todo todo =  new Todo(++todosCount,userName,description,targetDate,isDone);
        todos.add(todo);
    }
}
