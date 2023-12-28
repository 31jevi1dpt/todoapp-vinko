package de.hftstuttgart.springboottodovinko.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.hftstuttgart.springboottodovinko.models.ToDoItem;
import de.hftstuttgart.springboottodovinko.repositories.ToDoItemRepository;

@Component
public class ToDoItemDataLoader implements CommandLineRunner{
    
     private final Logger logger = LoggerFactory.getLogger(ToDoItemDataLoader.class);

    @Autowired
    ToDoItemRepository toDoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if (toDoItemRepository.count() == 0) {
            ToDoItem toDoItem1 = new ToDoItem("do the MT PVL");
            ToDoItem toDoItem2 = new ToDoItem("prepare presentation");

            toDoItemRepository.save(toDoItem1);
            toDoItemRepository.save(toDoItem2);
        }

        logger.info("Number of ToDoItems: {}", toDoItemRepository.count());
    }

}
