package com.hardy.todoapp.controllers;

import com.hardy.todoapp.models.Todo;
import com.hardy.todoapp.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    Logger logger = LoggerFactory.getLogger(TodoController.class);
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap map){
        String userName = getLoggedInUserName();
        List<Todo> todos =  todoService.findByUserName(userName);
        map.put("todos",todos);
        return "listTodos";
    }

    @GetMapping("add-todo")
    public String showAddTodoPage(ModelMap map){
        String user = getLoggedInUserName();
        Todo todo = new Todo(0, user,"",LocalDate.now().plusWeeks(1),false);
        map.put("todo",todo);
        return "todo";
    }

    @PostMapping("add-todo")
    public String listAllTodos(Todo todo){
        String userName = getLoggedInUserName();
        todoService.addTodo(
                userName,
                todo.getDescription(),
                todo.getTargetDate(),
                false
        );
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteById(id);
        return "redirect:list-todos";

    }
    @GetMapping("update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping("update-todo")
    public String updateTodo(ModelMap model, Todo todo) {

        String username = getLoggedInUserName();
        todo.setUserName(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private static String getLoggedInUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}
