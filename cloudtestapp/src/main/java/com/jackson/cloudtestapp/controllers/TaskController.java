package com.jackson.cloudtestapp.controllers;

import com.jackson.cloudtestapp.model.Task;
import com.jackson.cloudtestapp.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private static final Logger logger =
            LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Home page
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // Display all tasks
    @GetMapping("/tasks")
    public String viewTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    // Display add-task form
    @GetMapping("/tasks/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    // Save a new task
    @PostMapping("/tasks/save")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        logger.info("Task saved: {}", task.getTitle());
        return "redirect:/tasks";
    }

    // Display edit-task form
@GetMapping("/tasks/edit/{id}")
public String showEditTaskForm(@PathVariable("id") Long id, Model model) {
    Task task = taskService.getTaskById(id);

    if (task == null) {
        logger.warn("Task not found with ID: {}", id);
        return "redirect:/tasks";
    }

    model.addAttribute("task", task);
    return "edit-tasks";
}
    // Delete a task
@GetMapping("/tasks/delete/{id}")
public String deleteTask(@PathVariable("id") Long id) {
    taskService.deleteTask(id);
    logger.info("Task deleted with ID: {}", id);
    return "redirect:/tasks";
}
}