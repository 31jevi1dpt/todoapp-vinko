package de.hftstuttgart.springboottodovinko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.hftstuttgart.springboottodovinko.models.ToDoItem;
import de.hftstuttgart.springboottodovinko.repositories.ToDoItemRepository;

@Controller
public class ToDoFormController {
    
    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping("/create-todo")
    public String showCreateForm(ToDoItem toDoItem){
        return "add-todo-item";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        ToDoItem toDoItem = toDoItemRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found"));
    
        model.addAttribute("todo", toDoItem);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") long id, Model model) {
        ToDoItem toDoItem = toDoItemRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("ToDoItem ID: " + id + " not found"));
    
        toDoItemRepository.delete(toDoItem);
        return "redirect:/";
    }

    
}