package uia.arqsoft.h2example.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uia.arqsoft.h2example.model.entity.Task;
import uia.arqsoft.h2example.model.service.TaskService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "todolist")
public class ToDoListRest {
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "tasks")
    public List<Task> getTasks() {
        return taskService.getTask();
    }

    @GetMapping(value = "tasks/{id}")
    public Optional<Task> getTaskById(@PathVariable(name = "id") Long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping(value = "tasks/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable(name = "id") Long id) {
        try {
            taskService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "tasks")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            taskService.deleteAll();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> create (@RequestBody Task task) {
        try {
            Task myTask = taskService.save(new Task(task.getDescription(), task.getStatus(), task.getCreationDate()));
            return new ResponseEntity<>(myTask, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<Task> modify (@PathVariable(name = "id") Long id, @RequestBody Task task) {
        Optional<Task> taskData = taskService.getTaskById(id);
        if (taskData.isPresent()) {
            Task myTask = taskData.get();
            myTask.setDescription(task.getDescription());
            myTask.setStatus(task.getStatus());
            myTask.setCreationDate(task.getCreationDate());
            return new ResponseEntity<>(taskService.save(myTask), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
