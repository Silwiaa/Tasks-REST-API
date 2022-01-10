package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {
    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long taskId) {
        List<Task> allTasksList = getAllTasks();
        Task searchedTask = null;
        for (Task task : allTasksList) {
            if (task.getId() == taskId) {
                searchedTask = task;
            }
        }
        return searchedTask;
    }
}
