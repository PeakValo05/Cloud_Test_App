package com.jackson.cloudtestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jackson.cloudtestapp.model.Task;


// Repository interface for Task entity
public interface TaskRepository extends JpaRepository<Task, Long> {

}