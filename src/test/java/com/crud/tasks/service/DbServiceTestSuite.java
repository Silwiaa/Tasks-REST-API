package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldGetAllTasks() {
        //Given
        Task task = new Task(1L, "Title 1", "Content 1");
        Task task2 = new Task(2L, "Title 2", "Content 2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task2);

        when(repository.findAll()).thenReturn(tasks);

        //When
        List<Task> resultTasks = dbService.getAllTasks();

        //Then
        assertEquals(resultTasks.size(), 2);
    }

    @Test
    public void shouldFindTaskByIdTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "Title 1", "Content 1");

        when(repository.findById(task.getId())).thenReturn(java.util.Optional.of(task));

        //When
        Task resultTask = dbService.findTask(task.getId());

        //Then
        assertThat(resultTask).isNotNull();
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "Title 1", "Content 1");

        when(repository.save(task)).thenReturn(task);

        //When
        Task resultTask = dbService.saveTask(task);

        //Then
        assertEquals(resultTask.getId(), 1L);
        assertEquals(resultTask.getTitle(), "Title 1");
        assertEquals(resultTask.getContent(), "Content 1");
    }
}
