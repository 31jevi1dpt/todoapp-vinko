package de.hftstuttgart.springboottodovinko.repositories;

import org.springframework.data.repository.CrudRepository;

import de.hftstuttgart.springboottodovinko.models.ToDoItem;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {
}
