package com.Apex.Todo.service;

import com.Apex.Todo.models.Task;
import com.Apex.Todo.repository.TaskRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        logger.info("Fetching all tasks from the database:{}",taskRepository.findAll());
        return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task=new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
        logger.info("Created new task with title: {}",title);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
        logger.info("Deleted task with id: {}",id);
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("No id match"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);

    }
}
