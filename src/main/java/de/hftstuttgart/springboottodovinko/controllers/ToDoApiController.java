package de.hftstuttgart.springboottodovinko.controllers;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hftstuttgart.springboottodovinko.models.ToDoItem;
import de.hftstuttgart.springboottodovinko.repositories.ToDoItemRepository;

@RestController
@RequestMapping("/api/todos")
public class ToDoApiController {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping
    public ResponseEntity<Iterable<ToDoItem>> getAllTodos() {
        Iterable<ToDoItem> todos = toDoItemRepository.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItem> getTodoById(@PathVariable("id") long id) {
        ToDoItem toDoItem = toDoItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found"));
        return new ResponseEntity<>(toDoItem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ToDoItem> createTodoItem(@RequestBody ToDoItem toDoItem) {
        toDoItem.setCreatedDate(Instant.now());
        toDoItem.setModifiedDate(Instant.now());
        toDoItemRepository.save(toDoItem);
        return new ResponseEntity<>(toDoItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoItem> updateTodoItem(@PathVariable("id") long id, @RequestBody ToDoItem updatedToDoItem) {
        ToDoItem existingToDoItem = toDoItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found"));

        existingToDoItem.setDescription(updatedToDoItem.getDescription());
        existingToDoItem.setComplete(updatedToDoItem.isComplete());
        existingToDoItem.setModifiedDate(Instant.now());

        toDoItemRepository.save(existingToDoItem);
        return new ResponseEntity<>(existingToDoItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable("id") long id) {
        ToDoItem toDoItem = toDoItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found"));

        toDoItemRepository.delete(toDoItem);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
