package com.hardy.todoapp.controllers;

import com.hardy.todoapp.models.Todo;
import com.hardy.todoapp.repository.TodoRepository;
import com.hardy.todoapp.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJPA {
    Logger logger = LoggerFactory.getLogger(TodoControllerJPA.class);
    private TodoService todoService;
    private TodoRepository todoRepository;

    public TodoControllerJPA(TodoService todoService,TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap map){
        String userName = getLoggedInUserName();
        List<Todo> todos = todoRepository.findByUserName(userName);
        logger.info(todos.toString());
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
        todo.setUserName(userName);
        todoRepository.save(todo);

        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:list-todos";

    }
    @GetMapping("update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping("update-todo")
    public String updateTodo(ModelMap model, Todo todo) {

        String username = getLoggedInUserName();
        todo.setUserName(username);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    private static String getLoggedInUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName().toLowerCase();
    }
}
