package com.jackson.cloudtestapp.service;

import com.jackson.cloudtestapp.model.Task;
import com.jackson.cloudtestapp.repository.TaskRepository;
import org.springframework.stereotype.Service;


@Service    
public class TaskService {


    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
