package com.jackson.cloudtestapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jackson.cloudtestapp.model.Task;
import com.jackson.cloudtestapp.service.TaskService;

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

    
    logger.info("Entering TaskController.viewTasks");

    model.addAttribute("tasks", taskService.getAllTasks());

    logger.info("Exiting TaskController.viewTasks");

    return "tasks";
}






    // Display add-task form
    @GetMapping("/tasks/add")
    public String showAddTaskForm(Model model) {

        

        logger.info("Entering TaskController.showAddTaskForm");


        model.addAttribute("task", new Task());

        logger.info("Displaying add-task form");

        logger.info("Exiting TaskController.showAddTaskForm");

        return "add-task";
    }






    // Save a new task
    @PostMapping("/tasks/save")
    public String saveTask(@ModelAttribute("task") Task task, RedirectAttributes redirectAttribute) {
        taskService.saveTask(task);
        logger.info("Task saved: {}", task.getTitle());
        redirectAttribute.addFlashAttribute("successMessage", "Task saved successfully!");

        logger.info("Redirecting to view all tasks");
        logger.info("Exiting TaskController.saveTask");
        return "redirect:/tasks";
    }





    // Display edit-task form
@GetMapping("/tasks/edit/{id}")
public String showEditTaskForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttribute) {
    Task task = taskService.getTaskById(id);

    if (task == null) {
        logger.warn("Task not found with ID: {}", id);
        
        redirectAttribute.addFlashAttribute("errorMessage", "Task not found!");

        return "redirect:/tasks";
    }

    model.addAttribute("task", task);
    logger.info("Displaying edit-task form for task with ID: {}", id);

    logger.info("Exiting TaskController.showEditTaskForm");

    return "edit-tasks";
}





    // Delete a task
@GetMapping("/tasks/delete/{id}")
public String deleteTask(@PathVariable("id") Long id, RedirectAttributes redirectAttribute) {
    taskService.deleteTask(id);
    logger.info("Task deleted with ID: {}", id);

    redirectAttribute.addFlashAttribute("successMessage", "Task deleted successfully!");

    logger.info("Redirecting to view all tasks after deletion");

    logger.info("Exiting TaskController.deleteTask");
    return "redirect:/tasks";
}
}