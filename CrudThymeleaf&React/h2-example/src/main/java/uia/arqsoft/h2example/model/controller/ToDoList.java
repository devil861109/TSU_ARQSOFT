package uia.arqsoft.h2example.model.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uia.arqsoft.h2example.model.entity.Task;
import uia.arqsoft.h2example.model.exception.TaskNotFoundException;
import uia.arqsoft.h2example.model.service.TaskService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@Slf4j
@RequestMapping(value = "list")
public class ToDoList {
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "tasks")
    public String getTasks(Model model) {
        List<Task> list = taskService.getTask();
        model.addAttribute("table", list);
        return "tasks/index";
    }

    @GetMapping(value = "tasks/{id}")
    public String getTaskById(@PathVariable(name = "id") Long id, Model model) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            List<Task> list = new ArrayList<>();
            list.add(task.get());
            log.info("Controller - getTaskById - task {}", list);
            model.addAttribute("table", list);
        } else
            model.addAttribute("table", null);
        return "tasks/index";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteTaskById(@PathVariable(name = "id") Long id, Model model) {
        taskService.deleteById(id);
        List<Task> list = taskService.getTask();
        model.addAttribute("table", list);
        return "tasks/index";
    }

    @GetMapping(value = "new")
    public String newTask(Model model) {
        Task myTask = new Task();
        model.addAttribute("theObject", myTask);
        return "tasks/form";
    }

    @GetMapping(value = "update/{id}")
    public String updateTask (@PathVariable(name = "id") Long id, Model model) throws IOException {
        Optional<Task> taskData = taskService.getTaskById(id);
        if (taskData.isPresent()) {
            Task myTask = taskData.get();
            model.addAttribute("theObject", myTask);
        } else {
            model.addAttribute("table", null);
        }
        return "tasks/form";
    }

    @PostMapping(value = "save")
    public String saveTask (@ModelAttribute("theObject") Task task, Model model) throws IOException {
        log.info("Controller - save - task {}", task);
        log.info("Controller - save - task id {}", task.getId());
        log.info("Controller - save - task description {}", task.getDescription());
        log.info("Controller - save - task status {}", task.getStatus());
        /*if (task.getId() != null) {
            //update
            Task myTask =
        } else {

        }*/
        Task myTask = taskService.save(task);
        List<Task> list = new ArrayList<>();
        list.add(myTask);
        model.addAttribute("table", list);
        return "tasks/index";
    }
}
