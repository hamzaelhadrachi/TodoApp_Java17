package com.hardy.todoapp.controllers;

import com.hardy.todoapp.models.Todo;
import com.hardy.todoapp.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap map){
        List<Todo> todos =  todoService.findByUserName("Hamza");
        map.put("todos",todos);
        return "listTodos";
    }

    @GetMapping("add-todo")
    public String showAddTodoPage(ModelMap map){
        String user = (String) map.get("name");
        Todo todo = new Todo(0, user,"",LocalDate.now().plusWeeks(1),false);
        map.put("todo",todo);
        return "todo";
    }

    @PostMapping("add-todo")
    public String listAllTodos(Todo todo, ModelMap map){
        String userName = (String) map.get("name");
        todoService.addTodo(
                userName,
                todo.getDescription(),
                LocalDate.now().plusWeeks(2),
                false
        );
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        //Delete todo

        todoService.deleteById(id);
        return "redirect:list-todos";

    }
}
